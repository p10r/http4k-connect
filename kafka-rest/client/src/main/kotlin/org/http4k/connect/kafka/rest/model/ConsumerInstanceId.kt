package org.http4k.connect.kafka.rest.model

import dev.forkhandles.values.NonBlankStringValueFactory
import dev.forkhandles.values.StringValue

class ConsumerInstanceId private constructor(override val value: String) : StringValue(value) {
    companion object : NonBlankStringValueFactory<ConsumerInstanceId>(::ConsumerInstanceId)
}
