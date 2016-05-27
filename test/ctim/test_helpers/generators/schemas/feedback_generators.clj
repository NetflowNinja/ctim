(ns ctim.test-helpers.generators.schemas.feedback-generators
  (:require [clojure.test.check.generators :as gen]
            [ctim.lib.time :as time]
            [ctim.schemas
             [feedback :refer [NewFeedback Feedback]]
             [common :as schemas-common]]
            [ctim.test-helpers.generators.common
             :refer [complete leaf-generators maybe]
             :as common]
            [ctim.test-helpers.generators.id :as gen-id]))

(def gen-feedback
  (gen/fmap
   (fn [id]
     (complete
      Feedback
      {:id id}))
   (gen-id/gen-short-id-of-type :feedback)))

(def gen-new-feedback
  (gen/fmap
   (fn [[id entity-id]]
     (complete
      NewFeedback
      (cond-> {}
        id (assoc :id id)
        entity-id (assoc :entity_id entity-id))))
   (gen/tuple
    (gen-id/gen-short-id-of-type :feedback)
    (gen-id/gen-short-id-of-type :judgement))))
