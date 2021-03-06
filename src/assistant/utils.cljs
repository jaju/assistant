(ns assistant.utils
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [cljs.core.async :refer [<! >! chan]]))

(def fs (js/require "fs"))

(defn- get-text [tags]
  (apply str (filter #(> (count (clojure.string/trim %)) 0)
               (flatten (map (fn [tag] (let [t (goog/typeOf tag)
                                             cv (:content tag)]
                                         (if (= t "string")
                                           tag
                                           (if cv (get-text cv) "")))) tags)))))

(defn user-home []
  (let [node-env (.-env js/process)]
    (or (.-HOME node-env) (.-HOMEPATH node-env) (.-USERPROFILE node-env))))

(defn create-if-not-exist
  "create file if the file not exists"
  [file-name]
  (if-not (.existsSync fs file-name)
    (.writeFileSync fs file-name "" "utf-8")))

(defn write-to-file [file-name content]
  (.writeFileSync fs file-name content "utf-8"))

(defn get-namespace-name [content]
  "get the namespace name from given conten string"
  (last (.match content #"ns (.*) ")))


(defn available-plugins []
  (let [dir (str js/__dir__ "./src/assistant/services")
        files (.readdirSync fs dir)]
    (print files)))


