package org.acme.book

import java.util.UUID

data class Book(
        var id: UUID = UUID.randomUUID(),
        var title: String,
        var author: String,
        var year: Short
)
