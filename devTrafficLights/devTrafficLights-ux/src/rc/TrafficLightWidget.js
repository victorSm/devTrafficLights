/**
 * A module defining `TrafficLightWidget`.
 *
 * @module nmodule/devTrafficLight/rc/TrafficLightWidget
 */
define(['bajaux/Widget',
        'bajaux/mixin/subscriberMixIn',
        'd3',
        'css!nmodule/devTrafficLights/rc/TrafficLight'], function (
        Widget,
        subscriberMixIn,
        d3) {

  'use strict';

  /**
   * Description of your widget.
   *
   * @class
   * @extends module:bajaux/Widget
   * @alias module:nmodule/devTrafficLight/rc/TrafficLightWidget
   */
  var TrafficLightWidget = function () {
    var that = this;
    // This calls the super constructor.
    Widget.apply(that, arguments);

    // Applying a Subscriber MixIn adds subscription capabilities to a
    // Widget. Once added, the value passed into 'doLoad' is fully subscribed
    // and 'live'.
    subscriberMixIn(that);
  };

  //extend and set up prototype chain
  TrafficLightWidget.prototype = Object.create(Widget.prototype);
  TrafficLightWidget.prototype.constructor = TrafficLightWidget;

////////////////////////////////////////////////////////////////
// Widget
////////////////////////////////////////////////////////////////

  /**
   * Describe how your widget does its initial setup of the DOM.
   *
   * @param {jQuery} jq The DOM element into which to load this Widget
   */
  TrafficLightWidget.prototype.doInitialize = function (element) {

    var that = this;

    element.addClass("d3_TrafficLightWidgetOuter");

    var svgContainer = d3.select(element[0])
                .append('svg')
                .attr('top', 0)
                .attr('left', 0)
                .attr('width', "100%")
                .attr('height', "100%")
                .attr('class', 'd3_TrafficLightWidgetOuter')
                .append('g');

    svgContainer.append("rect")
                .attr("id", "d3_lightContainer")
                .attr("x", 1)
                .attr("y", 1)
                .attr("width", 38)
                .attr("height", 108);

    svgContainer.append("circle")
                .attr("id", "d3_redLight")
                .attr("cx", 20)
                .attr("cy", 20)
                .attr("r", 15);
    svgContainer.append("circle")
                .attr("id", "d3_yellowLight")
                .attr("cx", 20)
                .attr("cy", 54)
                .attr("r", 15);
    svgContainer.append("circle")
                .attr("id", "d3_greenLight")
                .attr("cx", 20)
                .attr("cy", 88)
                .attr("r", 15);

  };


  /**
   * Describe how your widget loads in a value.
   *
   * @param {baja.Component} in this case the enum component loaded into the widget.
   */
  TrafficLightWidget.prototype.doLoad = function(component) {

    var that = this;
    that.getSubscriber().attach("changed", function(property) {
      if (property.getName() === "out") {
        updateLights(that, component, property);
      }
    });

    updateLights(that, component, null);
  };


  /**
   * Called when the layout of the Widget changes.
   */
  TrafficLightWidget.prototype.doLayout = function(component) {
    resize(this);
  };


  // Update the traffic lights, using "changedProperty" if possible (mostly when called from changed),
  // and from the underlying "component" (mostly when called from doLoad).
  function updateLights(widget, component, changedProperty)
  {
    //console.log(" updateLights, component: " + component + ", changedProperty: " + changedProperty);

    var jq = widget.jq(),
        red = jq.find('#d3_redLight'),
        yellow = jq.find('#d3_yellowLight'),
        green = jq.find('#d3_greenLight'),
        changedPropertyValue,
        enumValue;


    if (changedProperty) {
        try {
          changedPropertyValue = component.get(changedProperty)
        } catch (err) {
          //console.log(" updateLights, component.get(changedProperty) err: " + err);
        }
    }

    try
    {
      if (changedProperty && component && changedPropertyValue)
      {
        if (typeof changedPropertyValue.getTag === "function")
        {
          enumValue = changedPropertyValue.getTag();
        }
        else if (typeof changedPropertyValue.getValue === "function")
        {
          enumValue = changedPropertyValue.getValue().getTag();
        }
        else
        {
          return;
        }
      }
      else if (component)
      {
        if (typeof component.getTag === "function")
        {
          enumValue = component.getTag();
        }
        else if (typeof component.getOut === "function")
        {
          enumValue = component.getOut().getValue().getTag();
        }
        else if (typeof component.getValue === "function")
        {
          enumValue = component.getValue().getTag();
        }
      }
    }
    catch(err)
    {
      //console.log(" updateLights, err: " + err + ", component: " + component);
      throw err;
    }

    //console.log(" enumValue is [" + enumValue + "]");

    // set all lights to inactive
    red.attr({ class: "d3_inactive"});
    yellow.attr({ class: "d3_inactive"});
    green.attr({ class: "d3_inactive"});

    switch (enumValue) {
      case ("red"):
          red.attr({ class: "d3_active"});
          break;
      case ("yellow"):
          yellow.attr({ class: "d3_active"});
          break;
      case ("green"):
          green.attr({ class: "d3_active"});
          break;
      default:
          // do nothing
    }
  }

  function resize(widget) {

    var jq = widget.jq(),
        scaleFactor = jq.width() / 40;


    d3.select( jq.find('#d3_lightContainer').get(0) )
                              .attr("width", jq.width()-2 )
                              .attr("height", jq.height()-2);


    // scale to width ...

    // red light
    d3.select( jq.find('#d3_redLight').get(0) )
                            .attr("cx", 20 * scaleFactor)
                            .attr("cy", 19 * scaleFactor)
                            .attr("r", 15 * scaleFactor);
    // yellow light
    d3.select( jq.find('#d3_yellowLight').get(0) )
                            .attr("cx", 20 * scaleFactor)
                            .attr("cy", 53 * scaleFactor)
                            .attr("r", 15 * scaleFactor);
    // green light
    d3.select( jq.find('#d3_greenLight').get(0) )
                            .attr("cx", 20 * scaleFactor)
                            .attr("cy", 87 * scaleFactor)
                            .attr("r", 15 * scaleFactor);
  }

  return TrafficLightWidget;
});
