(defproject tickets-cqrs "1.0.0-SNAPSHOT"
  :description "a cqrs ticket playground"
  :dependencies [[org.clojure/clojure "1.2.0"]
                 [org.clojure/clojure-contrib "1.2.0"]
                 [org.clojars.nathell/redis-clojure "1.2.7"]
                 [clj-time "0.3.0-SNAPSHOT"]
                 [clj-stacktrace "0.2.1"]]
  :dev-dependencies [[swank-clojure "1.4.0-SNAPSHOT"]
                     [criterium "0.0.1-SNAPSHOT"]
                     [clojure-source "1.2.1"]
                     ]
  :jvm-opts ["-agentlib:jdwp=transport=dt_socket,server=y,suspend=n"]
  :main tickets-cqrs.core)
