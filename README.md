# A web interface for the [SharkPKI](https://github.com/SharedKnowledge/SharkPKI). WIP.

## Conceptual idea

The concept is open for discussion.

![Diagram of components](/doc/model.png "Diagram of components")

- The GUI for managing the PKI (e.g., inspecting peers and assigning signing failures) runs in the web browser and is hosted by a simple web server on the localhost.
- The MainActivity is a very basic, native android UI with a button for starting/ stopping the service (i. e. the web server and the API) for the web GUI. 
    - The MainActivity also manages the SharkPeer and the SharkPKI. It executes commands received from the web GUI directly on the SharkPKI.
    - The API and web server run in the background until the user stops them via the button.
