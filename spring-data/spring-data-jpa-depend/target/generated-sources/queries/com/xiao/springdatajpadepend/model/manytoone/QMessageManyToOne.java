package com.xiao.springdatajpadepend.model.manytoone;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMessageManyToOne is a Querydsl query type for MessageManyToOne
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMessageManyToOne extends EntityPathBase<MessageManyToOne> {

    private static final long serialVersionUID = -872554964L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMessageManyToOne messageManyToOne = new QMessageManyToOne("messageManyToOne");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath info = createString("info");

    public final QPersonManyToOne personManyToOne;

    public QMessageManyToOne(String variable) {
        this(MessageManyToOne.class, forVariable(variable), INITS);
    }

    public QMessageManyToOne(Path<? extends MessageManyToOne> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMessageManyToOne(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMessageManyToOne(PathMetadata metadata, PathInits inits) {
        this(MessageManyToOne.class, metadata, inits);
    }

    public QMessageManyToOne(Class<? extends MessageManyToOne> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.personManyToOne = inits.isInitialized("personManyToOne") ? new QPersonManyToOne(forProperty("personManyToOne")) : null;
    }

}

