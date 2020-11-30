package org.acme.config;


import org.acme.data.Book;
import org.infinispan.protostream.SerializationContextInitializer;
import org.infinispan.protostream.annotations.AutoProtoSchemaBuilder;

@AutoProtoSchemaBuilder(includeClasses = {Book.class}, schemaPackageName = "book_sample")
public interface BookContextInitializer extends SerializationContextInitializer {
}
