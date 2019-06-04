package com.acme.devTrafficLights.ux;

import javax.baja.naming.BOrd;
import javax.baja.nre.annotations.AgentOn;
import javax.baja.nre.annotations.NiagaraSingleton;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.BSingleton;
import javax.baja.sys.Context;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;
import javax.baja.web.BIFormFactorMax;
import javax.baja.web.js.BIJavaScript;
import javax.baja.web.js.JsInfo;

@NiagaraType(
        agent = @AgentOn(
                types = {"devTrafficLights:TrafficLightController"}
        )
)

@NiagaraSingleton

public class BTrafficLightView extends BSingleton implements BIJavaScript,BIFormFactorMax {
    /*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
    /*@ $com.acme.devTrafficLights.ux.BTrafficLightView(1673270855)1.0$ @*/
    /* Generated Wed Aug 02 12:40:00 CDT 2017 by Slot-o-Matic (c) Tridium, Inc. 2012 */

    public static final BTrafficLightView INSTANCE = new BTrafficLightView();

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////

    @Override
    public Type getType() { return TYPE; }
    public static final Type TYPE = Sys.loadType(BTrafficLightView.class);

    /*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

    public JsInfo getJsInfo(Context ctx) {
        return jsInfo;
    }

    private static final JsInfo jsInfo = JsInfo.make(BOrd.make("module://devTrafficLights/rc/TrafficLightView.js"));
}
