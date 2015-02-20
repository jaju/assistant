(defproject assistant "0.1.0-SNAPSHOT"
  :description "A super awesome extensible personal assistant"
  :url "http://example.com/FIXME"

  :dependencies [[org.clojure/clojure "1.7.0-alpha5"]
                 [org.clojure/clojurescript "0.0-2850"]
                 [org.clojure/core.async "0.1.346.0-17112a-alpha"]
                 [cljs-http "0.1.26"]
                 [com.cognitect/transit-cljs "0.8.205"]
                 [markdown-clj "0.9.62" :exclusions [org.clojure/clojure]]
                 [domina "1.0.3"]
                 [garden "1.2.5"]
                 [prismatic/dommy "1.0.0"]
                 [hickory "0.5.4"]
                 [om "0.7.0"]]

  :plugins [[lein-cljsbuild "1.0.4"]
            [lein-node-webkit-build "0.1.6"]]

  :source-paths ["src"]

  :node-webkit-build {:root "./public"
                      :name "Assistant"
                      :osx {:icon "./public/images/icon.icns"}
                      :platforms #{:osx}
                      :disable-developer-toolbar true
                      :nw-version "0.11.6"}

  :cljsbuild {
    :builds [{:id "assistant"
              :source-paths ["src" "plugins"]
              :compiler {
                         :output-to "public/assistant.js"
                         :output-dir "public/out"
                         :source-map true
                         :optimizations :none}}]})
