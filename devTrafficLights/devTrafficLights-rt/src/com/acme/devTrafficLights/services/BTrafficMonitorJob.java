package com.acme.devTrafficLights.services;

import com.acme.devTrafficLights.BTrafficLightColor;
import com.acme.devTrafficLights.services.BTrafficMonitorService;

import javax.baja.collection.*;
import javax.baja.history.*;
import javax.baja.history.db.*;
import javax.baja.job.*;
import javax.baja.naming.*;
import javax.baja.sys.*;
import javax.baja.nre.annotations.*;

/**
 * Created by R. Derek Otieno on february 17, 2018
 */

@NiagaraType
public class BTrafficMonitorJob extends BSimpleJob
{
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.acme.devTrafficLights.BTrafficMonitorJob(2979906276)1.0$ @*/
/* Generated Thu May 02 11:30:16 EDT 2019 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BTrafficMonitorJob.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

    /**
     * Callback when this job is invoked.  For this job, the goal is to loop
     * through all histories that have an enumerated record type matching
     * the BTrafficLightColor frozen enum range, then count the number of
     * transitions in each history (a transition is defined as a change in
     * value), and finally make a call back to the BTrafficMonitorService to
     * update statistics stored in memory (also persisting them to a file).
     */
    @SuppressWarnings("unchecked")
    @Override
    public void run(Context cx) throws Exception
    {
        // Find the BTrafficMonitorService by a special Sys.getService() call
        BTrafficMonitorService tmService = (BTrafficMonitorService)Sys.getService(BTrafficMonitorService.TYPE);
        // Reset its transition count stats as they're about to be recomputed
        tmService.clearTransitionCounts();

        // Use the log() to write status messages that the user can see
        // This is the BJob log, rather than Logger for the station console
        log().start("Reset transition stats, start recomputing...");

        // Get the BHistoryService (starting place for accessing histories)
        BHistoryService historyService = (BHistoryService)Sys.getService(BHistoryService.TYPE);
        if (historyService == null)
            // If you have an error in a BSimpleJob, simply throw an exception
            throw new IllegalStateException("History Service not available.");

        // Get the BHistoryDatabase from the BHistoryService, as it manages
        // the API for history access, so we’ll need it later
        BHistoryDatabase historyDb = historyService.getDatabase();
        if (historyDb == null)
            throw new IllegalStateException("History database not available.");

        // Prepare a bql statement to find the histories of interest.  We only
        // care about histories that have an enumerated record type matching
        // the BTrafficLightColor frozen enum range
        StringBuffer bql = new StringBuffer();
        bql.append("history:|bql:select * from sys.histories");
        bql.append(" where recordType='history:EnumTrendRecord' and ");
        bql.append(" valueFacets.range='");
        bql.append(BTrafficLightColor.TYPE.toString()).append("'");
        BOrd ord = BOrd.make(bql.toString()); // Put the BQL query in ORD form

        // Resolve the BQL ORD- it's ok to assume the result is a BICollection
        //BICollection historyConfigs = (BICollection)ord.get(historyService);
        BITable historyConfigs = (BITable)ord.get(historyService);

        // Initialize some counts used for progress updates
        // Since N4, to learn the size we need to convert to a RandomAccessTable,
        // loading whole collection into memory, we could use another BQL query
        // to achieve the same but this is most likely to return a consistent result.
        BIRandomAccessTable historyConfigsRat = Tables.slurp(historyConfigs);
        int total = historyConfigsRat.size();
        int count = 0;

        // Now we want to loop through the results to calculate statistics.
        // We do this using a cursor retrieved from the BICollection
        //Cursor cursor = historyConfigs.cursor();
        try(TableCursor<BIObject> cursor = historyConfigs.cursor())
        {

            while(cursor.next())
            {
                // While in progress, check to see if the user canceled this job.
                // If canceled, throw a special exception (JobCancelException)
                if (canceled) throw new JobCancelException();

                // Callback to update progress of the job for user visualization
                progress((int)(((double)count / (double)total) * 100.0));

                // Retrieve the next element in the cursor (because of our
                // query, we can safely cast it to a BHistoryConfig instance)
                BHistoryConfig config = (BHistoryConfig)cursor.get();
                BHistoryId id = config.getId(); // Retrieve the history's ID

                // Retrieve the history from the history database and scan
                // its records to compute the number of transitions it contains
                try (HistorySpaceConnection conn = historyDb.getConnection(cx))
                {
                    BIHistory history = conn.getHistory(id);

                    Cursor records = conn.scan(history); // Cursor of history records
                    int current = -1; // Stores the current record's ordinal value
                    if (records.next())
                    { // Initialize "current" var to first record's ordinal value
                        // (because of our query, we safely assume BEnumTrendRecords)
                        BEnumTrendRecord rec = (BEnumTrendRecord)records.get();
                        current = rec.getValue().getOrdinal();
                    }
                    int transitions = 0; // Tallies the transitions for the history
                    while (records.next()) // Scan all records of the history
                    {
                        // Check for user cancellation in this loop too
                        if (canceled) throw new JobCancelException();

                        // If this record's ordinal value is different from the last
                        // record's, increment the transition count
                        BEnumTrendRecord rec = (BEnumTrendRecord)records.get();
                        int value = rec.getValue().getOrdinal();
                        if (current != value)
                            transitions++;
                        current = value;
                    }
                    // Completed scanning a history, so update its transition count
                    // statistic stored in the BTrafficMonitorService
                    tmService.setTransitionCount(id, transitions);

                    // log() another status message that the user can see
                    log().message("Found " + transitions + " transitions for history " + id);

                }
            }
        }
        // We're all done now, so force a persistent save of the
        // transition statistics we pushed into memory on the
        // BTrafficMonitorService by invoking its save() action
        tmService.save();

        // Indicate job completion to the user
        log().message("Saved results to file "+tmService.getStatisticsFile());
        progress(100); // Causes progress bar to go to 100%
    }

    /**
     * Callback when the user cancels this active job.
     */
    public void doCancel(Context cx)
    { // Overridden here to set the canceled flag
        canceled = true;
        super.doCancel(cx);
    }

    // This flag gets set if the user cancels the active job
    private boolean canceled = false;
}