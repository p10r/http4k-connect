package org.http4k.connect.kafka.rest

import org.http4k.connect.kafka.rest.action.KafkaRestAction
import org.http4k.core.Credentials
import org.http4k.core.HttpHandler
import org.http4k.core.Uri
import org.http4k.core.then
import org.http4k.filter.ClientFilters.BasicAuth
import org.http4k.filter.ClientFilters.SetHostFrom

/**
 * Standard HTTP implementation of KafkaRest Proxy
 */
fun KafkaRest.Companion.Http(
    credentials: Credentials,
    baseUri: Uri,
    http: HttpHandler
) = object : KafkaRest {
    private val http = BasicAuth(credentials)
        .then(SetHostFrom(baseUri))
        .then(http)

    override fun <R : Any> invoke(action: KafkaRestAction<R>) = action.toResult(this.http(action.toRequest()))
}
