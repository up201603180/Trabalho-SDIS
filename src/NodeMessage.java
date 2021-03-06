final class NodeMessage {

    public final int nodeId;
    public final String message;

    public NodeMessage( int nodeId, String message ) {
        this.nodeId = nodeId;
        this.message = message;
    }

    public int getNodeId() {
        return nodeId;
    }

    public String getMessage() {
        return message;
    }
}
