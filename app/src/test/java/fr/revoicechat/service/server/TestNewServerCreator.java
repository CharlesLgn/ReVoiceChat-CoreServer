package fr.revoicechat.service.server;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import fr.revoicechat.model.Room;
import fr.revoicechat.model.Server;
import fr.revoicechat.repository.RoomRepository;
import fr.revoicechat.repository.ServerRepository;

@ExtendWith({MockitoExtension.class, SoftAssertionsExtension.class})
class TestNewServerCreator {

  @Mock private ServerRepository serverRepository;
  @Mock private RoomRepository roomRepository;
  @InjectMocks private NewServerCreator creator;

  @Test
  void test(SoftAssertions softly) {
    // Given
    List<Room> rooms = new ArrayList<>();
    doAnswer(invocationOnMock -> {
      Room room = invocationOnMock.getArgument(0);
      rooms.add(room);
      return room;
    }).when(roomRepository).save(any());
    Server server = new Server();
    // When
    creator.create(server);
    // Then
    softly.assertThat(server.getId()).isNotNull();
    assertThat(rooms).hasSize(2);
    var room1 = rooms.getFirst();
    softly.assertThat(room1.getId()).isNotNull();
    softly.assertThat(room1.getName()).isEqualTo("General");
    softly.assertThat(room1.getServer()).isEqualTo(server);
    var room2 = rooms.getLast();
    softly.assertThat(room2.getId()).isNotNull();
    softly.assertThat(room2.getName()).isEqualTo("Random");
    softly.assertThat(room2.getServer()).isEqualTo(server);
    verify(serverRepository).save(server);
    verify(roomRepository).save(room1);
    verify(roomRepository).save(room2);
    verifyNoMoreInteractions(serverRepository, roomRepository);
  }
}