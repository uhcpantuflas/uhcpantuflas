# UHC Pantuflas Minecraft Plugin

[![Java](https://img.shields.io/badge/Java-17%2B-orange.svg)](https://www.oracle.com/java/)
[![Spigot API](https://img.shields.io/badge/Spigot-1.20.2--R0.1--SNAPSHOT-yellow.svg)](https://hub.spigotmc.org/)
[![Javadoc](https://img.shields.io/badge/docs-javadoc-blue.svg)](https://uhcpantuflas.github.io/uhcpantuflas/)
[![License: MIT](https://img.shields.io/badge/License-MIT-green.svg)](LICENSE.md)

An open-source architectural prototype and game engine core built in Java for running Ultra Hardcore (UHC) matches on Spigot/PaperMC servers.

This project was developed as a proof of concept (PoC) focusing on modular Object-Oriented Design (OOD), event-driven decoupled systems, and clean domain state management over the Bukkit/Spigot API.

---

## Technical Architecture & Design Patterns

The plugin follows a modular package structure separating domain logic, world manipulation, and event handling:

* **Command Pattern:** Commands and autocompletion routines (`CommandExecutor`, `TabCompleter`) are fully decoupled into dedicated classes (`UHCTeamCommandExecutor`, `UHCConfigCommandExecutor`, etc.) rather than bloating the main class.
* **Finite State Machine (FSM):** Match flow is coordinated through centralized states (`GameState`) inside `GameplayManager` to control progression from lobby/preparation to active combat and endgame.
* **Observer / Event-Driven Architecture:** Domain rules are mapped across **11 decoupled event listeners** (e.g., player movement restrictions, combat cancellation during grace periods, nether portal bounds, and custom crafting recipes).
* **Builder Pattern:** Implemented in `MinecraftTextBuilder` for fluent generation and serialisation of chat components.
* **Custom Domain Exception Hierarchy:** Robust error handling in configuration and team management via specialized exceptions (`UHCConfigException`, `UHCTeamSizeExceededException`, etc.), isolating business rule violations from runtime crashes.

--

## Repository Structure

```text
src/main/java/com/github/uhcpantuflas/uhcpantuflas/
├── UHCPantuflas.java             # Plugin lifecycle management (onEnable / onDisable)
├── config/                       # Strongly-typed configuration parser & exceptions
│   └── exceptions/
├── gameplay/                     # Match state machine, timers, and rules
├── player/                       # Player session lifecycle and state tracking
├── teams/                        # Team rosters, limits, and procedural banner assets
├── world/                        # World border scaling, schematic pasting & chunk pregen
└── utils/text/                   # Custom text serialization and builder utilities
```

## Build & Installation

### Prerequisites

* **Java Development Kit (JDK):** 17 or higher
* **Apache Maven:** 3.8+
* **Target Server:** Spigot / Paper 1.20.2 (or 1.20.x compatible)

### Compilation

Build the production-ready shaded JAR with Maven:

```bash
mvn clean package

```

The compiled artifact will be located at `target/uhcpantuflas-<version>.jar`.

### Server Deployment

1. Copy `target/uhcpantuflas-<version>.jar` into your server's `plugins/` directory.
2. Ensure required server-side dependencies are present:
* [Chunky](https://github.com/pop4959/Chunky) (v1.3.92+)
* [WorldEdit](https://dev.bukkit.org/projects/worldedit) (v7.2.9+)
3. Start the server.

---

## Project Status

**Current Maturity:** `Alpha. Architecture Prototype (PoC)`

The core architecture, compilation pipeline, and base listeners are fully verified. For a detailed breakdown of completed systems, known technical debt, and pending refactoring, refer to [PROJECT_STATE.md](PROJECT_STATE.md).

Full API reference is available at the [Javadoc Documentation](https://uhcpantuflas.github.io/uhcpantuflas/).

---

## Authors & Maintenance

* **Original Project:** [uhcpantuflas](https://github.com/uhcpantuflas)
* **Core Architecture & Development:** Gorka Puente ([@gorkiiuss](https://github.com/gorkiiuss))

---

## License

Distributed under the [MIT License](LICENSE.md).