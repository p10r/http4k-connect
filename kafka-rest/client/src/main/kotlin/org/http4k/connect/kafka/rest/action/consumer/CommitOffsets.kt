package org.http4k.connect.kafka.rest.action.consumer

import org.http4k.connect.Http4kConnectAction
import org.http4k.connect.kClass
import org.http4k.connect.kafka.rest.KafkaRestMoshi.auto
import org.http4k.connect.kafka.rest.model.CommitOffset
import org.http4k.connect.kafka.rest.model.CommitOffsetsSet
import org.http4k.core.Body
import org.http4k.core.ContentType
import org.http4k.core.KAFKA_JSON_V2
import org.http4k.core.Method.POST
import org.http4k.core.Request
import org.http4k.core.with

@Http4kConnectAction
data class CommitOffsets(val offsets: List<CommitOffset>) : KafkaRestConsumerAction<Unit>(kClass()) {
    override fun toRequest() = Request(POST, "/consumers/{group}/instances/{instance}/offsets")
        .with(Body.auto<CommitOffsetsSet>(contentType = ContentType.KAFKA_JSON_V2).toLens() of CommitOffsetsSet(offsets))
}
