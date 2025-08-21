package fr.revoicechat.junit;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.junit.jupiter.api.extension.ExtendWith;

@Retention(RetentionPolicy.RUNTIME)
@ExtendWith(ClearDataBaseExtension.class)
@ExtendWith(ConnectUserExtension.class)
public @interface ClearDbAndConnectUser {
}