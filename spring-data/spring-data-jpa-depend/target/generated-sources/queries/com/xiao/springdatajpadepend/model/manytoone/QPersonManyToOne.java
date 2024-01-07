package com.xiao.springdatajpadepend.model.manytoone;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QPersonManyToOne is a Querydsl query type for PersonManyToOne
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPersonManyToOne extends EntityPathBase<PersonManyToOne> {

    private static final long serialVersionUID = -1934312848L;

    public static final QPersonManyToOne personManyToOne = new QPersonManyToOne("personManyToOne");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public QPersonManyToOne(String variable) {
        super(PersonManyToOne.class, forVariable(variable));
    }

    public QPersonManyToOne(Path<? extends PersonManyToOne> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPersonManyToOne(PathMetadata metadata) {
        super(PersonManyToOne.class, metadata);
    }

}

