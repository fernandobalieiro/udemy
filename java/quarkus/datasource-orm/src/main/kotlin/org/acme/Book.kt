package org.acme

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType.IDENTITY
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "book", schema = "hello")
data class Book(
        var name: String,
        var author: String,
        var pages: Int
) {
        @Id @GeneratedValue(strategy = IDENTITY)
        var id: Int? = null
}
