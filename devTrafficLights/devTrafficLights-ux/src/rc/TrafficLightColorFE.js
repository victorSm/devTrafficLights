/**
        * A module defining `TrafficLightColorFE`.
        *
        * @module nmodule/devTrafficLight/rc/TrafficLightColorFE
        */
        define(['bajaux/Widget',
        'nmodule/webEditors/rc/fe/baja/BaseEditor',
        'baja!devTrafficLights:TrafficLightColor',
        'baja!',
        'hbs!nmodule/devTrafficLights/rc/TrafficLightColorFETemplate',
        'css!nmodule/devTrafficLights/rc/TrafficLight'], function (
        Widget,
        BaseEditor,
        types,
        baja,
        template) {

        'use strict';

        var trafficLightColorEnum = baja.$("devTrafficLights:TrafficLightColor");

        /**
        * Description of your widget.
        *
        * @class
        * @extends module:bajaux/Widget
        * @alias module:nmodule/devTrafficLight/rc/TrafficLightColorFE
        */
        var TrafficLightColorFE = function () {
        var that = this;

        that.$currentColor;
        that.$updatedColor;

        // This calls the super constructor.
        BaseEditor.apply(that, arguments);
        };

        //extend and set up prototype chain
        TrafficLightColorFE.prototype = Object.create(BaseEditor.prototype);
        TrafficLightColorFE.prototype.constructor = TrafficLightColorFE;

        ////////////////////////////////////////////////////////////////
        // Widget
        ////////////////////////////////////////////////////////////////

        /**
        * Describe how your widget does its initial setup of the DOM.
        *
        * @param {jQuery} dom The DOM element into which to load this Widget
        */
        TrafficLightColorFE.prototype.doInitialize = function (dom) {

        var that = this,
        object = that.object;

        dom.html(template({
        changeButtonRedText: trafficLightColorEnum.get(1).getDisplayTag(),
        changeButtonYellowText: trafficLightColorEnum.get(2).getDisplayTag(),
        changeButtonGreenText: trafficLightColorEnum.get(3).getDisplayTag()
        }));

        dom.on('click', '#example-traffic-light-fe-button-red', function () {
        that.setNewValue(trafficLightColorEnum.get(1));
        return false;
        });
        dom.on('click', '#example-traffic-light-fe-button-yellow', function () {
        that.setNewValue(trafficLightColorEnum.get(2));
        return false;
        });

        dom.on('click', '#example-traffic-light-fe-button-green', function () {
        that.setNewValue(trafficLightColorEnum.get(3));
        return false;
        });

        };

        TrafficLightColorFE.prototype.setNewValue = function(newValue) {

        this.$updatedColor = newValue;

        if(this.$currentColor !== this.$updatedColor)
        {
        this.setModified(true);
        this.$value = this.$updatedColor;
        this.load(this.$value);
        }
        }

        /**
        * Describe how your widget loads in a value.
        *
        * @param {baja.Component} in this case the enum component loaded into the widget.
        */
        TrafficLightColorFE.prototype.doLoad = function(component) {

        var jq = this.jq();

        this.$currentColor = component;
        this.$updatedColor = component; // set default/initial value

        jq.html(template({
        currentColor: this.$currentColor.getDisplayTag(),
        changeButtonRedText: trafficLightColorEnum.get(1).getDisplayTag(),
        changeButtonYellowText: trafficLightColorEnum.get(2).getDisplayTag(),
        changeButtonGreenText: trafficLightColorEnum.get(3).getDisplayTag()
        }));

        };

        TrafficLightColorFE.prototype.doSave = function(validValue, params) {
        this.$value = validValue;
        };

        /* called before doSave */
        TrafficLightColorFE.prototype.doRead = function(readValue, params) {
        return this.$updatedColor;
        };

        return TrafficLightColorFE;
        });