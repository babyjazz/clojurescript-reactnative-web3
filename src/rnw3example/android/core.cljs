(ns rnw3example.android.core
  (:require [reagent.core :as r :refer [atom]]
            [re-frame.core :refer [subscribe dispatch dispatch-sync]]
            [rnw3example.events]
            [rnw3example.subs]))

(js/require "./shim.js")
(js/require "./global.js")
(js/require "crypto")
(def ReactNative (js/require "react-native"))

(def app-registry (.-AppRegistry ReactNative))
(def text (r/adapt-react-class (.-Text ReactNative)))
(def view (r/adapt-react-class (.-View ReactNative)))
(def image (r/adapt-react-class (.-Image ReactNative)))
(def touchable-highlight (r/adapt-react-class (.-TouchableHighlight ReactNative)))
(defn alert [title]
      (.alert (.-Alert ReactNative) title))

(def web-url "http://161.246.11.135:8545")
(def Web3 (js/require "web3"))
(def web3 (new Web3))
(.setProvider web3 (new web3.providers.HttpProvider web-url))

(defn app-root []
  (let [greeting (subscribe [:get-greeting])]
    (fn []
      [view {:style {:flex-direction "column" :margin 40 :align-items "center"}}
       [text {:style {:font-size 30 :font-weight "100" :margin-bottom 20 :text-align "center"}} @greeting]
       [touchable-highlight {:style {:background-color "#999" :padding 10 :border-radius 5}
                             :on-press #(.. web3 -eth (getAccounts (fn [e r] (prn r))))}
        [text {:style {:color "white" :text-align "center" :font-weight "bold"}} "press me"]]])))

(defn init []
      (dispatch-sync [:initialize-db])
      (.registerComponent app-registry "RNW3Example" #(r/reactify-component app-root)))
