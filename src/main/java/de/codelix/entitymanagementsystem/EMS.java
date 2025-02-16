package de.codelix.entitymanagementsystem;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import de.codelix.entitymanagementsystem.dto.CreateTeamDto;
import de.codelix.entitymanagementsystem.dto.TeamCreateData;
import de.codelix.entitymanagementsystem.http.JsonBodyHandler;
import de.codelix.entitymanagementsystem.http.Response;
import de.codelix.entitymanagementsystem.models.*;
import lombok.val;

import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class EMS {
    private static final HttpClient client = HttpClient.newHttpClient();
    private static final ObjectWriter ow = new ObjectMapper().setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE).writer();
    private static final String ROOT;
    static {
        String host = System.getenv("API_HOST");
        String port = System.getenv("API_PORT");
        ROOT = String.format("http://%s:%s/", host, port);
    }

    public static void main(String[] args) {
        val ems = new EMS();
        ems.getEntities()
                .thenAccept(System.out::println).join();
    }

    public CompletableFuture<Entity> createEntity(String name) {
        try {
            Entity entity = new Entity(null, name);
            String params = ow.writeValueAsString(entity);
            HttpRequest build = HttpRequest.newBuilder()
                    .uri(URI.create(ROOT + "entities"))
                    .POST(HttpRequest.BodyPublishers.ofString(params))
                    .build();
            return client.sendAsync(build, new JsonBodyHandler<>(new TypeReference<Response<Entity>>() {}))
            .thenApply(HttpResponse::body);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public CompletableFuture<Entity> getEntity(int entityId) {
        HttpRequest build = HttpRequest.newBuilder().uri(URI.create(ROOT + "entities/" + entityId)).build();
        return client.sendAsync(build, new JsonBodyHandler<>(new TypeReference<Response<Entity>>() {}))
                .thenApply(HttpResponse::body);
    }

    public CompletableFuture<List<Entity>> getEntities() {
        HttpRequest build = HttpRequest.newBuilder().uri(URI.create(ROOT + "entities")).build();
        return client.sendAsync(build, new JsonBodyHandler<>(new TypeReference<Response<List<Entity>>>() {}))
                .thenApply(HttpResponse::body);
    }

    public CompletableFuture<Void> renameEntity(int entityId, String newName) {
        try {
            Entity member = new Entity(entityId, newName);
            String params = ow.writeValueAsString(member);
            HttpRequest build = HttpRequest.newBuilder()
                    .uri(URI.create(ROOT + "entities/" + entityId))
                    .PUT(HttpRequest.BodyPublishers.ofString(params))
                    .build();
            return client.sendAsync(build, new JsonBodyHandler<>(new TypeReference<Response<Void>>() {}))
                    .thenApply(HttpResponse::body);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public CompletableFuture<Member> getMemberByEntityId(int entityId) {
        HttpRequest build = HttpRequest.newBuilder().uri(URI.create(ROOT + "entities/" + entityId + "/member")).build();
        return client.sendAsync(build, new JsonBodyHandler<>(new TypeReference<Response<Member>>() {}))
                .thenApply(HttpResponse::body);
    }

    public CompletableFuture<Void> leaveTeam(int entityId) {
        HttpRequest build = HttpRequest.newBuilder()
                .uri(URI.create(ROOT + "entities/" + entityId + "/team"))
                .DELETE().build();
        return client.sendAsync(build, new JsonBodyHandler<>(new TypeReference<Response<Void>>() {}))
                .thenApply(HttpResponse::body);
    }

    public CompletableFuture<List<MemberInvite>> getInvitesByEntityId(int entityId) {
        HttpRequest build = HttpRequest.newBuilder().uri(URI.create(ROOT + "entities/" + entityId + "/invites")).build();
        return client.sendAsync(build, new JsonBodyHandler<>(new TypeReference<Response<List<MemberInvite>>>() {}))
                .thenApply(HttpResponse::body);
    }

    public CompletableFuture<MemberInvite> createInvite(MemberInvite invite) {
        try {
            String params = ow.writeValueAsString(invite);
            HttpRequest build = HttpRequest.newBuilder()
                    .uri(URI.create(ROOT + "invites"))
                    .POST(HttpRequest.BodyPublishers.ofString(params))
                    .build();
            return client.sendAsync(build, new JsonBodyHandler<>(new TypeReference<Response<MemberInvite>>() {}))
                    .thenApply(HttpResponse::body);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public CompletableFuture<MemberInvite> getInvite(int inviteId) {
        HttpRequest build = HttpRequest.newBuilder().uri(URI.create(ROOT + "invites/" + inviteId)).build();
        return client.sendAsync(build, new JsonBodyHandler<>(new TypeReference<Response<MemberInvite>>() {}))
                .thenApply(HttpResponse::body);
    }

    public CompletableFuture<Member> acceptInvite(int inviteId) {
        HttpRequest build = HttpRequest.newBuilder()
                .uri(URI.create(ROOT + "invites/" + inviteId))
                .POST(HttpRequest.BodyPublishers.noBody()).build();
        return client.sendAsync(build, new JsonBodyHandler<>(new TypeReference<Response<Member>>() {}))
                .thenApply(HttpResponse::body);
    }

    public CompletableFuture<Void> declineInvite(int inviteId) {
        HttpRequest build = HttpRequest.newBuilder()
                .uri(URI.create(ROOT + "invites/" + inviteId))
                .DELETE().build();
        return client.sendAsync(build, new JsonBodyHandler<>(new TypeReference<Response<Void>>() {}))
                .thenApply(HttpResponse::body);
    }

    public CompletableFuture<List<Member>> getMembers() {
        HttpRequest build = HttpRequest.newBuilder().uri(URI.create(ROOT + "members")).build();
        return client.sendAsync(build, new JsonBodyHandler<>(new TypeReference<Response<List<Member>>>() {}))
                .thenApply(HttpResponse::body);
    }

    public CompletableFuture<Member> getMember(int memberId) {
        HttpRequest build = HttpRequest.newBuilder().uri(URI.create(ROOT + "members/" + memberId)).build();
        return client.sendAsync(build, new JsonBodyHandler<>(new TypeReference<Response<Member>>() {}))
                .thenApply(HttpResponse::body);
    }

    public CompletableFuture<Void> deleteMember(int memberId) {
        HttpRequest build = HttpRequest.newBuilder().uri(URI.create(ROOT + "members/" + memberId)).DELETE().build();
        return client.sendAsync(build, new JsonBodyHandler<>(new TypeReference<Response<Void>>() {}))
                .thenApply(HttpResponse::body);
    }

    public CompletableFuture<List<Team>> getTeams() {
        HttpRequest build = HttpRequest.newBuilder().uri(URI.create(ROOT + "teams")).build();
        return client.sendAsync(build, new JsonBodyHandler<>(new TypeReference<Response<List<Team>>>() {}))
                .thenApply(HttpResponse::body);
    }

    public CompletableFuture<TeamCreateData> createTeam(String name, float hue, int entityId) {
        try {
            CreateTeamDto createTeamDto = new CreateTeamDto(name, hue, entityId);
            String params = ow.writeValueAsString(createTeamDto);
            System.out.println(params);
            HttpRequest build = HttpRequest.newBuilder()
                    .uri(URI.create(ROOT + "teams"))
                    .POST(HttpRequest.BodyPublishers.ofString(params))
                    .build();
            return client.sendAsync(build, new JsonBodyHandler<>(new TypeReference<Response<TeamCreateData>>() {}))
                    .thenApply(HttpResponse::body);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public CompletableFuture<Team> getTeam(int teamId) {
        HttpRequest build = HttpRequest.newBuilder().uri(URI.create(ROOT + "teams/" + teamId)).build();
        return client.sendAsync(build, new JsonBodyHandler<>(new TypeReference<Response<Team>>() {}))
                .thenApply(HttpResponse::body);
    }

    public CompletableFuture<Team> getTeamByEntityId(int entityId) {
        HttpRequest build = HttpRequest.newBuilder().uri(URI.create(ROOT + "entities/" + entityId + "/team")).build();
        return client.sendAsync(build, new JsonBodyHandler<>(new TypeReference<Response<Team>>() {}))
                .thenApply(HttpResponse::body);
    }

    public CompletableFuture<Void> recolorTeam(int teamId, float hue) {
        try {
            Team team = new Team(teamId, null, hue);
            String params = ow.writeValueAsString(team);
            HttpRequest build = HttpRequest.newBuilder()
                    .uri(URI.create(ROOT + "teams/" + teamId + "/color"))
                    .PUT(HttpRequest.BodyPublishers.ofString(params))
                    .build();
            return client.sendAsync(build, new JsonBodyHandler<>(new TypeReference<Response<Void>>() {}))
                    .thenApply(HttpResponse::body);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public CompletableFuture<Void> renameTeam(int teamId, String name) {
        try {
            Team team = new Team(teamId, name, null);
            String params = ow.writeValueAsString(team);
            HttpRequest build = HttpRequest.newBuilder()
                    .uri(URI.create(ROOT + "teams/" + teamId + "/name"))
                    .PUT(HttpRequest.BodyPublishers.ofString(params))
                    .build();
            return client.sendAsync(build, new JsonBodyHandler<>(new TypeReference<Response<Void>>() {}))
                    .thenApply(HttpResponse::body);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public CompletableFuture<List<Member>> getMembers(int teamId) {
        HttpRequest build = HttpRequest.newBuilder().uri(URI.create(ROOT + "teams/" + teamId + "/members")).build();
        return client.sendAsync(build, new JsonBodyHandler<>(new TypeReference<Response<List<Member>>>() {}))
                .thenApply(HttpResponse::body);
    }

    public CompletableFuture<List<MemberInvite>> getInvitesByTeamId(int teamId) {
        HttpRequest build = HttpRequest.newBuilder().uri(URI.create(ROOT + "teams/" + teamId + "/invites")).build();
        return client.sendAsync(build, new JsonBodyHandler<>(new TypeReference<Response<List<MemberInvite>>>() {}))
                .thenApply(HttpResponse::body);
    }

    public CompletableFuture<Token> createToken(int entityId) {
        try {
            Token team = new Token(entityId, null);
            String params = ow.writeValueAsString(team);
            HttpRequest build = HttpRequest.newBuilder()
                    .uri(URI.create(ROOT + "tokens"))
                    .POST(HttpRequest.BodyPublishers.ofString(params))
                    .build();
            return client.sendAsync(build, new JsonBodyHandler<>(new TypeReference<Response<Token>>() {}))
                    .thenApply(HttpResponse::body);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public CompletableFuture<Token> getToken(int entityId) {
        HttpRequest build = HttpRequest.newBuilder().uri(URI.create(ROOT + "tokens?pin=" + entityId)).build();
        return client.sendAsync(build, new JsonBodyHandler<>(new TypeReference<Response<Token>>() {}))
                .thenApply(HttpResponse::body);
    }
}
