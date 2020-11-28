package org.acme.config

import org.hibernate.search.backend.elasticsearch.analysis.ElasticsearchAnalysisConfigurationContext
import org.hibernate.search.backend.elasticsearch.analysis.ElasticsearchAnalysisConfigurer

class AnalysisConfigurer : ElasticsearchAnalysisConfigurer {
    override fun configure(context: ElasticsearchAnalysisConfigurationContext) {
        context.analyzer("name").custom()
                .tokenizer("standard")
                .tokenFilters("asciifolding", "lowercase")

        context.analyzer("english").custom()
                .tokenizer("standard")
                .tokenFilters("asciifolding", "lowercase", "porter_stem")

        context.normalizer("sort").custom()
                .tokenFilters("asciifolding", "lowercase")
    }
}
