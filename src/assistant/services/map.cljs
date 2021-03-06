(ns assistant.services.map
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [assistant.core :refer [register-card register-dispatcher register-css config valid-config]]
            [cljs.core.async :refer [<! >! chan]]
            [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]))


(def api-key (-> (config)
               :map :key))


(def config-err-msg "Please make sure you have google developer key in your
                    ~/.assistant file,{:map {:key '...'}} you can find a example in dot_assistant_example.
                    You can also head to https://developers.google.com/maps/documentation/embed/guide#api_key to know more about how to get the google developer key.")

(defn map-dispatcher [result-chan text]
  (if (valid-config [:map :key] config-err-msg)
    (go
      (>! result-chan {:type :map :content (str "https://www.google.com/maps/embed/v1/place?key=" api-key "&q=" text) :input text}))))

(defn map-view [data owner]
  (reify
    om/IRender
    (render [_]
      (dom/iframe #js {:src (:content data)} nil))))


(register-dispatcher :map map-dispatcher "map [place] -- show the map of given place name")
(register-card :map map-view)
(register-css [:.map
               [:iframe {:width "100%" :height "400px" :border "none"}]
               [:img {:margin-top "2px auto"}]])
