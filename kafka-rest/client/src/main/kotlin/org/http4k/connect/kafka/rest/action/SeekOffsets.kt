package org.http4k.connect.kafka.rest.action

import org.http4k.connect.Http4kConnectAction
import org.http4k.connect.kClass
import org.http4k.connect.kafka.rest.KafkaRestMoshi.auto
import org.http4k.connect.kafka.rest.model.ConsumerGroup
import org.http4k.connect.kafka.rest.model.ConsumerInstanceId
import org.http4k.connect.kafka.rest.model.SeekOffset
import org.http4k.connect.kafka.rest.model.SeekOffsetsSet
import org.http4k.core.Body
import org.http4k.core.ContentType
import org.http4k.core.KAFKA_JSON_V2
import org.http4k.core.Method.POST
import org.http4k.core.Request
import org.http4k.core.with

@Http4kConnectAction
data class SeekOffsets(
    val group: ConsumerGroup,
    val instance: ConsumerInstanceId,
    val offsets: List<SeekOffset>
) : KafkaRestAction<Unit>(kClass()) {
    override fun toRequest() = Request(POST, "/consumers/$group/instances/$instance/positions")
        .with(Body.auto<SeekOffsetsSet>(contentType = ContentType.KAFKA_JSON_V2).toLens() of SeekOffsetsSet(offsets))
}
