package de.quackr.persistence.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Quack.class)
public abstract class Quack_ {

	public static volatile SingularAttribute<Quack, Date> date;
	public static volatile SingularAttribute<Quack, String> backgroundColor;
	public static volatile SingularAttribute<Quack, User> author;
	public static volatile SingularAttribute<Quack, Long> id;
	public static volatile SingularAttribute<Quack, String> text;
	public static volatile SingularAttribute<Quack, String> title;
	public static volatile SingularAttribute<Quack, Boolean> publiclyVisible;

	public static final String DATE = "date";
	public static final String BACKGROUND_COLOR = "backgroundColor";
	public static final String AUTHOR = "author";
	public static final String ID = "id";
	public static final String TEXT = "text";
	public static final String TITLE = "title";
	public static final String PUBLICLY_VISIBLE = "publiclyVisible";

}

