(ns assistant.services.dribble
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [assistant.core :refer [register-card register-dispatcher register-css]]
            [cljs-http.client :as http]
            [cljs.core.async :refer [<! >! chan]]))

(def url "http://api.dribbble.com/shots/popular")

(def gui (js/require "nw.gui"))

(defn dribble-dispatcher [result-chan text]
  (go (let [response (<! (http/get url {:with-credentials? false}))
            m (-> response :body :shots)
            result (map #(assoc % :image (-> % :image_url)) m)]
        (when m
          (>! result-chan {:type :list-image-card :content result :input text})))))

(register-dispatcher :dribble dribble-dispatcher "dribble -- show popular designs from dribble")
