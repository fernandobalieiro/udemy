package org.acme

import io.quarkus.hibernate.orm.panache.kotlin.PanacheCompanion
import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntity
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "book", schema = "hello")
data class Book(
        var name: String,
        var author: String,
        var pages: Int
): PanacheEntity() {
    companion object: PanacheCompanion<Book, Long>  {
        fun findAllBooks() = findAll().list()
    }
}
