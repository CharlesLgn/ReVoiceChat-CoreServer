package fr.revoicechat.i18n;

import static fr.revoicechat.i18n.LocalizedMessageTestEnum.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.reflections.Reflections;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
class TestLocalizedMessage {

  @Test
  void testEnglishOnlyMessage() {
    assertThat(TEST_IN_ENGLISH_ONLY.translate()).isEqualTo("English");
    assertThat(TEST_IN_ENGLISH_ONLY.translate(List.of(Locale.CHINESE, Locale.ENGLISH))).isEqualTo("English");
    assertThat(TEST_IN_ENGLISH_ONLY.translate(List.of(Locale.FRENCH, Locale.ENGLISH))).isEqualTo("English");
  }

  @Test
  void testFrenchOnlyMessage() {
    assertThat(TEST_IN_FRENCH_ONLY.translate()).isEqualTo("TEST_IN_FRENCH_ONLY");
    assertThat(TEST_IN_FRENCH_ONLY.translate(List.of(Locale.CHINESE))).isEqualTo("TEST_IN_FRENCH_ONLY");
    assertThat(TEST_IN_FRENCH_ONLY.translate(List.of(Locale.FRENCH))).isEqualTo("Français");
  }

  @Test
  void testFrenchAndEnglishMessage() {
    assertThat(TEST_IN_FRENCH_AND_ENGLISH.translate()).isEqualTo("French and english");
    assertThat(TEST_IN_FRENCH_AND_ENGLISH.translate(List.of(Locale.CHINESE))).isEqualTo("French and english");
    assertThat(TEST_IN_FRENCH_AND_ENGLISH.translate(List.of(Locale.FRENCH))).isEqualTo("Français et anglais");
  }

  @Test
  void testWithNoTranslation() {
    assertThat(TEST_WITH_NO_TRANSLATION.translate()).isEqualTo("TEST_WITH_NO_TRANSLATION");
    assertThat(TEST_WITH_NO_TRANSLATION.translate(List.of(Locale.CHINESE))).isEqualTo("TEST_WITH_NO_TRANSLATION");
    assertThat(TEST_WITH_NO_TRANSLATION.translate(List.of(Locale.FRENCH))).isEqualTo("TEST_WITH_NO_TRANSLATION");
  }

  @Test
  void testWithNoTranslationFile() {
    assertThat(NotTranslatedTestEnum.TEST.translate()).isEqualTo("TEST");
    assertThat(NotTranslatedTestEnum.TEST.translate(List.of(Locale.CHINESE))).isEqualTo("TEST");
    assertThat(NotTranslatedTestEnum.TEST.translate(List.of(Locale.FRENCH))).isEqualTo("TEST");
  }

  private static Set<LocalizedMessage> localizedMessage() {
    return new Reflections("fr.revoicechat").getSubTypesOf(LocalizedMessage.class).stream()
                                            .filter(clazz -> !clazz.isAnnotationPresent(DoNotAddInTestList.class))
                                            .filter(Class::isEnum)
                                            .map(Class::getEnumConstants)
                                            .flatMap(Stream::of)
                                            .collect(Collectors.toSet());
  }

  @ParameterizedTest
  @MethodSource("localizedMessage")
  void testNotificationPayloadIsAnnotated(LocalizedMessage message) {
    assertThat(message.translate()).isNotEqualTo(message.name());
  }
}