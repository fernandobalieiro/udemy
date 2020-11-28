package org.acme

import com.fasterxml.jackson.annotation.JsonIgnore
import io.quarkus.hibernate.orm.panache.kotlin.PanacheCompanion
import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntity
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed
import javax.persistence.Entity
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Indexed
@Table(name = "book", schema = "hello")
data class Book(
        @FullTextField(analyzer = "english")
        var title: String,

        @field: JsonIgnore
        @field: ManyToOne
        var author: Author,

        var pages: Int
) : PanacheEntity() {
    companion object : PanacheCompanion<Book, Long> {
        fun findAllBooks() = findAll().list()
    }
}
