(ns tickets-cqrs.core
    (:require [redis.core :as redis]))


(def ticket-history
  '({:title "foo" :body "bar"}
    {:title "lark"}
    {:assignee 'roman :body "barbar"}
    {:priority 3}))


(defn ticket-key [id] (str "ticket:" id))

(defn save-ticket-events [ticket-id events]
  (doall (map #(redis/rpush (ticket-key ticket-id) %) events)))

(defn get-ticket-history [ticket-id]
  (map read-string
       (redis/lrange (ticket-key ticket-id) 0 -1)))

(defn -main []
  (try
    (redis/with-server {:host "127.0.0.1"}
      (let [ticket-id 123
            r-key (ticket-key ticket-id)]
        (redis/del r-key)
        (save-ticket-events ticket-id ticket-history)
        (println (reductions conj {}
                   (get-ticket-history ticket-id)))))
    (catch java.net.ConnectException e
      (binding [*out* *err*]
        (println "ERROR: Please start redis server manually!"))
      (System/exit 1))))

    ;(println (reductions conj {}
    ;                     (redis/lrange "ticket:123" 0 4)))))
