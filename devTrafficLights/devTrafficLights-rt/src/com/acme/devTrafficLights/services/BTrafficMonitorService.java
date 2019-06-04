package com.acme.devTrafficLights.services;
import com.acme.devTrafficLights.BTrafficMixin;

import java.io.IOException;
import java.util.Properties;

import javax.baja.file.*;
import javax.baja.history.*;
import javax.baja.job.*;
import javax.baja.naming.*;
import javax.baja.sys.*;
import javax.baja.nre.annotations.*;

import javax.baja.sys.BAbstractService;

/**
 * Created by R. Derek Otieno on February 17, 2018
 */

/**
 * The ord to the properties file to load/save the
 * traffic light transition statistics to/from memory.
 */
@NiagaraProperty(
        name = "statisticsFile",
        type = "baja:Ord",
        defaultValue = "BOrd.make(\"file:^traffic/trafficStats.properties\")",
        facets = {
//                @Facet(name = "String", value = "\"baja:IFile\"")
//                @Facet(name = "targetType", value = "\"baja:IFile\"")
//                @Facet(name = "TargetType", value = "\"baja:IFile\"")
//                @Facet(name = "BFacets.targetType", value = "\"baja:IFile\"")
                @Facet(name = BFacets.TARGET_TYPE, value = "\"baja:IFile\"")
        }
)

/**
 * Action to load the specified properties file into memory
 * (stores key/value pairs in memory for quick global access
 */
@NiagaraAction(
        name = "load"
)

/**
 * Action to save the statistics properties currently in memory
 * to the specified properties file in the form:
 *   <history device name>.<history name>=<count>
 */
@NiagaraAction(
        name = "save"
)

/**
 * Submits a job to the JobService which finds all
 * BEnumTrendRecord typed histories, computes their transition
 * count, and writes these values back out to the specified
 * properties file in the form:
 */
@NiagaraAction(
        name = "computeStatistics"
)

@NiagaraType
public class BTrafficMonitorService extends BAbstractService
{
    /*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
    /*@ $com.acme.devTrafficLights.BTrafficMonitorService(1259868452)1.0$ @*/
    /* Generated Sat Feb 17 12:50:01 EST 2018 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Property "statisticsFile"
////////////////////////////////////////////////////////////////

    /**
     * Slot for the {@code statisticsFile} property.
     * @see #getStatisticsFile
     * @see #setStatisticsFile
     */
//  public static final Property statisticsFile = newProperty(0, BOrd.make("file:^traffic/trafficStats.properties"), BFacets.make(TargetType, "baja:IFile"));
    public static final Property statisticsFile = newProperty(0, BOrd.make("file:^traffic/trafficStats.properties"), BFacets.make("targetType", "baja:IFile"));

    /**
     * Get the {@code statisticsFile} property.
     * @see #statisticsFile
     */
    public BOrd getStatisticsFile() { return (BOrd)get(statisticsFile); }

    /**
     * Set the {@code statisticsFile} property.
     * @see #statisticsFile
     */
    public void setStatisticsFile(BOrd v) { set(statisticsFile, v, null); }

////////////////////////////////////////////////////////////////
// Action "load"
////////////////////////////////////////////////////////////////

    /**
     * Slot for the {@code load} action.
     * Action to load the specified properties file into memory
     * (stores key/value pairs in memory for quick global access
     * @see #load()
     */
    public static final Action load = newAction(0, null);

    /**
     * Invoke the {@code load} action.
     * Action to load the specified properties file into memory
     * (stores key/value pairs in memory for quick global access
     * @see #load
     */
    public void load() { invoke(load, null, null); }

////////////////////////////////////////////////////////////////
// Action "save"
////////////////////////////////////////////////////////////////

    /**
     * Slot for the {@code save} action.
     * Action to save the statistics properties currently in memory
     * to the specified properties file in the form:
     * <history device name>.<history name>=<count>
     * @see #save()
     */
    public static final Action save = newAction(0, null);

    /**
     * Invoke the {@code save} action.
     * Action to save the statistics properties currently in memory
     * to the specified properties file in the form:
     * <history device name>.<history name>=<count>
     * @see #save
     */
    public void save() { invoke(save, null, null); }

////////////////////////////////////////////////////////////////
// Action "computeStatistics"
////////////////////////////////////////////////////////////////

    /**
     * Slot for the {@code computeStatistics} action.
     * Submits a job to the JobService which finds all
     * BEnumTrendRecord typed histories, computes their transition
     * count, and writes these values back out to the specified
     * properties file in the form:
     * @see #computeStatistics()
     */
    public static final Action computeStatistics = newAction(0, null);

    /**
     * Invoke the {@code computeStatistics} action.
     * Submits a job to the JobService which finds all
     * BEnumTrendRecord typed histories, computes their transition
     * count, and writes these values back out to the specified
     * properties file in the form:
     * @see #computeStatistics
     */
    public void computeStatistics() { invoke(computeStatistics, null, null); }

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////

    @Override
    public Type getType() { return TYPE; }
    public static final Type TYPE = Sys.loadType(BTrafficMonitorService.class);

    /*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

////////////////////////////////////////////////////////////////
// Action Implementations
////////////////////////////////////////////////////////////////

    /**
     * Load the properties file into memory.
     */
    public void doLoad()
    {
        // Retrieve the ORD (reference) to the properties file
        BOrd fileOrd = getStatisticsFile();
        try
        { // Try to resolve the file from its ORD (works if the file exists)
            BIFile file = (BIFile)fileOrd.get(this);
            // Clear old statistics stored in memory before loading from file
            statistics.clear();
            statistics.load(file.getInputStream()); // Load stats from file
        }
        catch(UnresolvedException e) // Indicates the file doesn't exist
        { // Call configFail() to set the service into fault
            configFail("Could not resolve statistics file. "+e.getMessage());
            return;
        }

        catch(IOException io) // Indicates problem reading file into memory
        {
            // Call configFail() to set the service into fault
            configFail("Error reading the statistics file. "+io.getMessage());
            return;
        }

        // load completed successfully, so call configOk() to notify service
        // its status is OK
        configOk();
    }


    /**
     * Save the properties file from memory.
     */
    public void doSave()
    {
        // Retrieve the ORD (reference) to the properties file
        BOrd fileOrd = getStatisticsFile();
        BIFile file = null;
        try
        { // Try to resolve the file from its ORD (works if the file exists)
            file = (BIFile)fileOrd.get(this);
        }
        catch(UnresolvedException e)
        { // If the statistics properties file doesn't exist,
            // that's fine, we'll create it below
        }

        if (file == null)
        { // If we couldn't resolve the properties file above, then that
            // means the file doesn't exist and we need to create it.
            try
            { // Parse the file ORD to retrieve the FilePath. We can safely
                // assume the FilePath is the last sub query of the full ORD.
                OrdQuery[] queries = fileOrd.parse();
                FilePath filePath = (FilePath)queries[queries.length - 1];
                // Once we have the FilePath, we use it to create the file.
                // Niagara provides a BFileSystem space which gives us access
                // to the local file system where we create the file:
                file = BFileSystem.INSTANCE.makeFile(filePath);
            }
            catch(Exception e) // Indicates problem creating file
            { // Call configFail() to set the service into fault
                configFail("Error creating the statistics file. "+e.getMessage());
                return;
            }
        }
        // If we get this far, then we have resolved the file, so now
        // we're ready to write the statistics to the file
        try
        { // Write the statistics from memory to the file
            statistics.store(file.getOutputStream(),
                    "Traffic Light Transition Counts");
        }
        catch(Exception e) // Indicates problem writing to file
        { // Call configFail() to set the service into fault
            configFail("Error writing to the statistics file. "+e.getMessage());
            return;
        }
        configOk(); // save completed successfully, so call configOk() to
        // notify service its status is OK
    }

    /**
     * When the computeStatistics action is invoked, it submits a new
     * BTrafficMonitorJob to the job service for processing on a separate
     * thread.
     */
    public void doComputeStatistics(Context cx)
    {
        BJobService.getService().submit(new BTrafficMonitorJob(), cx);
    }


////////////////////////////////////////////////////////////////
// BTrafficMonitorService API
////////////////////////////////////////////////////////////////

    /**
     * Return the computed transition count for the given history.
     * If the statistics haven't been computed yet, or the history
     * is not included in the statistics, -1 is returned.
     */
    public int getTransitionCount(BHistoryId id)
    {
        return Integer.parseInt(statistics.getProperty(toKeyName(id), "-1"));
    }

    /**
     * Assigns the given transition count to the provided history in memory
     */
    public void setTransitionCount(BHistoryId id, int count)
    {
        statistics.setProperty(toKeyName(id), Integer.toString(count));
    }

    /**
     * Clears the current transition counts currently held in memory
     */
    public void clearTransitionCounts()
    {
        statistics.clear();
    }
    /**
     * Private convenience method to return a key name string given a
     * history id.  The returned String is of the form:
     * <history device name>.<history name>
     */

    private static final String toKeyName(BHistoryId id)
    {
        StringBuffer sb = new StringBuffer();
        sb.append(id.getDeviceName()).append(".");
        sb.append(id.getHistoryName());
        return sb.toString();
    }

////////////////////////////////////////////////////////////////
// Service Callbacks
////////////////////////////////////////////////////////////////

    /**
     * Called when this service is first started.
     */
    @Override
    public void serviceStarted()
    {
        // On startup, we want to load the statistics from the persistent
        // file into memory, so we call the load() action to do this
        load();
        getComponentSpace().enableMixIn(BTrafficMixin.TYPE);
    }

    /**
     * Called when this service is stopped.
     */
    @Override
    public void serviceStopped()
    {
        // On shutdown, we want to save the current statistics in memory to
        // the file for persistence, so we call the save() action to do this
        save();
        getComponentSpace().disableMixIn(BTrafficMixin.TYPE);
    }

    /**
     * All services must implement this method to return the types to
     * be registered under.  The service will be automatically registered
     * during station bootstrap, and unregistered if unmounted.  The
     * component must extend or implement all of the types returned by this
     * method.  The result of this method should be static, that is it
     * should never change across instances nor over time.
     */
    @Override
    public Type[] getServiceTypes()
    { // Since this service only implements a single service Type (itself),
        // return a static array of one Type element
        return serviceTypes;
    }

    private static Type[] serviceTypes = new Type[] { TYPE };


////////////////////////////////////////////////////////////////
// Attributes
////////////////////////////////////////////////////////////////

    // Storage of the statistics in memory
    private Properties statistics = new Properties();
}