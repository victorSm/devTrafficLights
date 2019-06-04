/**
 * A module defining `TrafficLightView`.
 *
 * @module nmodule/devTrafficLight/rc/TrafficLightView
 */
define(['bajaux/Widget',
        'bajaux/mixin/subscriberMixIn',
        'nmodule/devTrafficLights/rc/TrafficLightWidget',
        'hbs!nmodule/devTrafficLights/rc/TrafficLightViewTemplate',
        'lex!devTrafficLights',
        'css!nmodule/devTrafficLights/rc/TrafficLight'], function (
        Widget,
        subscriberMixIn,
        TrafficLightWidget,
        template,
        lexicons) {

  'use strict';

  var lex = lexicons[0];

  function getOutValueDom(jq) {
    return jq.find('.example-traffic-light-view-out');
  }
  function getColorValueDom(jq) {
    return jq.find('.example-traffic-light-view-color');
  }
  function getMinimumYellowTimeValueDom(jq) {
    return jq.find('.example-traffic-light-view-minimumYellowTime');
  }
  function getTrafficLightWidgetDom(jq) {
    return jq.find('.example-traffic-light-view-widget-container');
  }

  /**
   * Description of your widget.
   *
   * @class
   * @extends module:bajaux/Widget
   * @alias module:nmodule/devTrafficLight/rc/TrafficLightView
   */
  var TrafficLightView = function () {
    var that = this;

    that.$tlWidget = new TrafficLightWidget();

    // This calls the super constructor.
    Widget.apply(that, arguments);

    // Applying a Subscriber MixIn adds subscription capabilities to a
    // Widget. Once added, the value passed into 'doLoad' is fully subscribed
    // and 'live'.
    subscriberMixIn(that);
  };

  //extend and set up prototype chain
  TrafficLightView.prototype = Object.create(Widget.prototype);
  TrafficLightView.prototype.constructor = TrafficLightView;

////////////////////////////////////////////////////////////////
// Widget
////////////////////////////////////////////////////////////////

  /**
   * Describe how your widget does its initial setup of the DOM.
   *
   * @param {jQuery} dom The DOM element into which to load this Widget
   */
  TrafficLightView.prototype.doInitialize = function (dom) {

    var widget = this;


    dom.html(template({
      outText: lex.get("devTrafficLightView.out"),
      colorText: lex.get("devTrafficLightView.color"),
      minimumYellowTimeText: lex.get("devTrafficLightView.minimumYellowTime")
    }));

    widget.$tlWidget.initialize(getTrafficLightWidgetDom(dom));
  };

  /**
   * Describe how your widget loads in a value.
   *
   * @param {baja.Component} in this case the enum component loaded into the widget.
   */
  TrafficLightView.prototype.doLoad = function(trafficLightController) {

    var widget = this,
        jq = widget.jq(),
        outValueDom = getOutValueDom(jq),
        colorValueDom = getColorValueDom(jq),
        minimumYellowTimeValueDom = getMinimumYellowTimeValueDom(jq);

    function update() {
      outValueDom.val(trafficLightController.getOutDisplay());
      colorValueDom.val(trafficLightController.getColorDisplay());
      minimumYellowTimeValueDom.val(trafficLightController.getMinimumYellowTimeDisplay());

      widget.$tlWidget.load(trafficLightController);
    }

    // Call update whenever a Property changes
    this.getSubscriber().attach('changed', update);

    // Call update for the first time.
    update();
  };


  TrafficLightView.prototype.doDestroy = function () {
    return this.$tlWidget.destroy();
  };

  return TrafficLightView;
});