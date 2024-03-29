(ns tickets-cqrs.core)

(def users
  [{:oid 1 :first "Tatsuhiro" :last "Ujihisa"}
   {:oid 2 :first "Roman" :last "Gonzalez"}
   {:oid 3 :first "Tavis" :last "Rudd"}])

(def statuses '(open closed))
(def core-fields '(title body assignee))

(def ticket-commands
  '(
    create
    set-title
    set-status
    set-assignee
    set-references                      ; links to other things orders, tickets, etc.

    composite))

(def empty-ticket {})
;;; ticket state is a map
(def scenario1
  [{:command 'create :title "new ticket"}
   {:command 'set-title :title "old ticket"}
   {:command 'set-assignee :assignee 1}
   ])

(defn transform-command-to-state [comm]
  (dissoc comm :command))

(defn ticket-state-accumulator-v1 [prev-state input]
  (conj prev-state (transform-command-to-state input)))

(defn test-it []
  (reductions ticket-state-accumulator-v1 empty-ticket scenario1)
  )
