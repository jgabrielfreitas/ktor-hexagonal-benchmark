package com.quick.tor.infrastructure.client

enum class Acks(val value: String) {
    Zero("0"),
    One("1"),
    All("all")
}

enum class BatchSize(val value: Int) {
    Sixteen(16 * 1024),
    ThirtyTwo(32 * 1024),
    SixtyFour(64 * 1024),
    OneHundredAndTwentyEight(1024 * 1024)
}

enum class CommandStatus(val value: String) {
    Open("Open"),
    Closed("Closed"),
    Processing("Processing"),
    Error("Error")
}

enum class Compression(val value: String) {
    None("none"),
    Gzip("gzip"),
    Snappy("snappy"),
    lz4("lz4"),
    zstd("zstd")
}

enum class OffsetBehaviour(val value: String) {
    Latest("latest"),
    Earliest("earliest"),
    None("none")
}
