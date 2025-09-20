package fr.revoicechat.risk.type;

import fr.revoicechat.i18n.LocalizedMessage;

/**
 * Define the risk type.
 * All hardcoded implementation are here to provide the list of risks and its translation.
 */
public interface RiskType extends LocalizedMessage {

  @Override
  default String fileName() {
    return RiskType.class.getCanonicalName();
  }

  default boolean isSameAs(final RiskType other) {
    return this.name().equals(other.name());
  }
}
