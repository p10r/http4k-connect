package org.http4k.connect.amazon.instancemetadata.action

import dev.forkhandles.result4k.Failure
import dev.forkhandles.result4k.Success
import org.http4k.connect.Http4kConnectAction
import org.http4k.connect.RemoteFailure
import org.http4k.connect.amazon.instancemetadata.model.HostName
import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status
import org.http4k.core.Uri

@Http4kConnectAction
class GetHostName: Ec2MetadataAction<HostName> {
    private val uri = Uri.of("/latest/meta-data/hostname")

    override fun toRequest() = Request(Method.GET, uri)

    override fun toResult(response: Response) = when(response.status) {
        Status.OK -> Success(response.value(HostName))
        else -> Failure(RemoteFailure(Method.GET, uri, response.status, response.bodyString()))
    }
}
