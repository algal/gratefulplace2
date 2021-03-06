(ns gratefulplace.db.t-maprules
  (:require [gratefulplace.db.test :as tdb]
            [gratefulplace.db.maprules :as mr]
            [flyingmachine.cartographer.core :as c]
            [gratefulplace.db.query :as q])
  (:use midje.sweet
        gratefulplace.controllers.test-helpers))

(setup-db-background)

(fact "topic serializer correctly serializes attributes"
  (c/mapify (q/one [:topic/title "First topic"]) mr/ent->topic)
  => (contains {:id number?
                :title "First topic"
                :post-count 2
                :author-id number?}))

(fact "topic serializer correctly serializes relationships"
  (:first-post (c/mapify (q/one [:topic/title "First topic"]) mr/ent->topic {:include :first-post}))
  => (contains {:content "T1 First post content"}))