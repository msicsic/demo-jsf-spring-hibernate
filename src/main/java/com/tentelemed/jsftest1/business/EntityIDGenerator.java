package com.tentelemed.jsftest1.business;

import org.apache.log4j.Logger;
import org.hibernate.dialect.Dialect;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import org.hibernate.type.IntegerType;
import org.hibernate.type.Type;

import java.io.Serializable;
import java.util.Properties;

@SuppressWarnings({"UnusedDeclaration"})
public class EntityIDGenerator extends SequenceStyleGenerator {

    private static final Logger logger = Logger.getLogger(EntityIDGenerator.class);

    private static EntityIDGenerator instance;
    private static boolean configured = false;

    private static final String defaultPrefix = "S1";
    public static String prefix;

    public EntityIDGenerator() {
        if (instance == null) {
            logger.info("EntityIDGenerator");
            instance = this;
        }
    }

    public void configure(Type type, Properties properties, Dialect dialect) {
        if (! configured) {
            configured = true;
            instance.__superConfigure(new IntegerType(), properties, dialect);
        }
        super.configure(new IntegerType(), properties, dialect);
    }

    public static String getPrefix() {
        return (prefix == null) ? defaultPrefix : prefix;
    }
    public static void setPrefix(String prefix) {
        EntityIDGenerator.prefix = prefix;
    }

    protected void __superConfigure(Type type, Properties properties, Dialect dialect) {
        super.configure(new IntegerType(), properties, dialect);
    }

    protected Serializable __superGenerate(SessionImplementor session, Object object) {
        return super.generate(session, object);
    }

    @Override
    public Serializable generate(SessionImplementor session, Object object) {
        return getPrefix() + "/" + instance.__superGenerate(session, object);
    }

    // todo - augment for synchro (reservations)
}
