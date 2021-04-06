package org.http4k.connect.amazon.events

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.http4k.connect.amazon.dynamodb.DynamoDbMoshi
import org.http4k.connect.amazon.dynamodb.action.Item
import org.http4k.connect.amazon.events.EventName.MODIFY
import org.http4k.connect.amazon.events.StreamViewType.NEW_AND_OLD_IMAGES
import org.http4k.connect.amazon.model.ARN
import org.http4k.connect.amazon.model.Attribute
import org.http4k.connect.amazon.model.Region
import org.http4k.testing.Approver
import org.http4k.testing.JsonApprovalTest
import org.http4k.testing.assertApproved
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(JsonApprovalTest::class)
class DynamoEventsTest {

    @Test
    fun `can roundtrip event`(approver: Approver) {
        val event = DynamodbEvent(
            listOf(
                StreamRecord(
                    "id", MODIFY, "version", "source", Region.of("us-east-1"),
                    Dynamodb(
                        Item(Attribute.boolean().required("theBool") of true),
                        Item(Attribute.int().required("theInt") of 123),
                        Item(Attribute.string().required("theString") of "hello"),
                        "123",
                        123,
                        NEW_AND_OLD_IMAGES
                    ),
                    ARN.of("arn:aws:sts:us-east-1:000000000001:role:myrole")
                )
            )
        )

        val json = DynamoDbMoshi.asFormatString(event)
        approver.assertApproved(json)
        assertThat(DynamoDbMoshi.asA(json), equalTo(event))
    }
}