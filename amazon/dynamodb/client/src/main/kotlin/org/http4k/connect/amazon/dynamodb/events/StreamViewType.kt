package org.http4k.connect.amazon.dynamodb.events

enum class StreamViewType {
    NEW_IMAGE, OLD_IMAGE, NEW_AND_OLD_IMAGES, KEYS_ONLY
}
