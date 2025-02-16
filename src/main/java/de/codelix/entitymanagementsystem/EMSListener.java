package de.codelix.entitymanagementsystem;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import de.codelix.entitymanagementsystem.models.MemberInvite;
import de.codelix.entitymanagementsystem.realtime.*;
import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

public abstract class EMSListener {
    private static final ObjectMapper OM = new ObjectMapper();
    public static final String ADDRESS;

    static {
        OM.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);

        String PUB_HOST = System.getenv("PUB_HOST");
        String PUB_PORT = System.getenv("PUB_PORT");

        ADDRESS = "tcp://" + PUB_HOST + ":" + PUB_PORT;
    }

    public EMSListener() {
    }

    public void listen() {
        try (ZContext context = new ZContext()) {
            // Socket to talk to clients
            ZMQ.Socket socket = context.createSocket(SocketType.SUB);
            socket.subscribe("");
            boolean connected = socket.connect(ADDRESS);
            if (!connected) {
                throw new IllegalStateException("Could not connect to " + ADDRESS);
            }

            while (!Thread.currentThread().isInterrupted()) {
                // Block until a message is received
                byte[] reply = socket.recv(0);
                String message = new String(reply, ZMQ.CHARSET);
                Action action;
                try {
                    action = OM.readValue(message, Action.class);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
                switch (action) {
                    case EntityRenameAction a:
                        this.onEntityRename(a);
                        break;
                    case MemberCreateAction a:
                        this.onMemberCreate(a);
                        break;
                    case MemberRemoveAction a:
                        this.onMemberRemove(a);
                        break;
                    case MemberInviteAction a:
                        this.onMemberInvite(a);
                        break;
                    case InviteDeclineAction a:
                        this.onInviteDecline(a);
                        break;
                    case InviteAcceptAction a:
                        this.onInviteAccept(a);
                        break;
                    case TeamCreateAction a:
                        this.onTeamCreate(a);
                        break;
                    case TeamRenameAction a:
                        this.onTeamRename(a);
                        break;
                    case TeamRecolorAction a:
                        this.onTeamRecolor(a);
                        break;
                    default:
                        throw new IllegalStateException("Unexpected Action type: " + action);
                }
            }
        }
    }

    protected abstract void onEntityRename(EntityRenameAction action);
    protected abstract void onMemberCreate(MemberCreateAction action);
    protected abstract void onMemberRemove(MemberRemoveAction action);
    protected abstract void onMemberInvite(MemberInviteAction action);
    protected abstract void onInviteDecline(InviteDeclineAction action);
    protected abstract void onInviteAccept(InviteAcceptAction action);
    protected abstract void onTeamCreate(TeamCreateAction action);
    protected abstract void onTeamRename(TeamRenameAction action);
    protected abstract void onTeamRecolor(TeamRecolorAction action);


}
