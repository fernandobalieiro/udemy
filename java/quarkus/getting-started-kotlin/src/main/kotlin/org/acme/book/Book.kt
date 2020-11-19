package org.acme.book

import java.util.UUID
import javax.validation.constraints.Max
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class Book(
        var id: UUID = UUID.randomUUID(),

        @field:NotBlank(message = "Title shouldn't be blank")
        var title: String,

        @field:NotBlank(message = "Author shouldn't be blank")
        var author: String,

        @field:Max(2020)
        @field:NotNull(message = "Year shouldn't be blank")
        var year: Short
)
