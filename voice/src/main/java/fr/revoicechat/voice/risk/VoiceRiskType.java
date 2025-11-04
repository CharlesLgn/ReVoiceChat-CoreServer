package fr.revoicechat.voice.risk;

import fr.revoicechat.risk.type.RiskCategory;
import fr.revoicechat.risk.type.RiskType;

@RiskCategory("VOICE_RISK_TYPE")
public enum VoiceRiskType implements RiskType {
  JOIN_VOICE_ROOM,
  SEND_IN_VOICE_ROOM,
  RECEIVE_IN_VOICE_ROOM
}
