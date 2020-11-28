package org.acme

import com.fasterxml.jackson.databind.PropertyNamingStrategy.SnakeCaseStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming
import io.quarkus.hibernate.orm.panache.kotlin.PanacheCompanion
import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntity
import org.hibernate.search.engine.backend.types.Sortable.YES
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.IndexedEmbedded
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.KeywordField
import javax.persistence.CascadeType.ALL
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType.EAGER
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Indexed
@Table(name = "author", schema = "hello")
@JsonNaming(SnakeCaseStrategy::class)
data class Author(
        @field: FullTextField(analyzer = "name")
        @field: KeywordField(name = "firstName_sort", sortable = YES, normalizer = "sort")
        @field: Column(name = "first_name")
        var firstName: String,

        @field: FullTextField(analyzer = "name")
        @field: KeywordField(name = "lastName_sort", sortable = YES, normalizer = "sort")
        @Column(name = "last_name")
        var lastName: String
) : PanacheEntity() {
    @field: OneToMany(cascade = [ALL], fetch = EAGER, orphanRemoval = true, mappedBy = "author")
    @field: IndexedEmbedded
    var books: List<Book>? = null

    companion object : PanacheCompanion<Author, Long> {
        fun findAllBooks() = findAll().list()
    }
}
