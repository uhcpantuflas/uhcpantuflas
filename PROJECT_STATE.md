# Project State & Technical Roadmap

**Current Version:** `0.1.0-ALPHA (Proof of Concept)`  
**Target Environment:** Spigot / Paper 1.20.2 (Java 17+)

This document provides a transparent overview of the current implementation status, architectural milestones achieved, and identified technical debt based on codebase audits.

---

## Subsystem Breakdown

| Subsystem / Module | Status | Details |
| :--- | :---: | :--- |
| **Compilation & Build (Maven)** | **Complete** | Canonically configured `pom.xml`, remote CodeMC repository integration, shaded artifact generation, and automated Javadoc export. |
| **Event Routing (Listeners)** | **Complete** | 11 specialized listeners registered covering damage logic, crafting overrides, portal validation, and lifecycle hooks. |
| **Asset & Banner Generation** | **Complete** | Procedural team banners with custom Bukkit patterns and layer blending (`BannerManager`). |
| **Configuration Engine** | **Functional** | In-memory key-value management with a custom domain exception hierarchy (`UHCConfigException`). |
| **State Machine (`GameState`)** | **Partial** | Core transitions between `BEGINNING` and `PLAYING` established. Intermediate phases (`LOBBY`, `PREPARATION`, `ENDING`) pending formalization. |
| **World & Border Pipeline** | **Partial** | Dynamic border diameter calculation and Chunky async integration wired; schematic lobby loading requires dynamic fallback handling. |
| **Team Lifecycle (`TeamManager`)** | **Partial** | Roster limits, member assignment, and mathematical spawn distribution implemented. |

---

## Architectural Debt & Refactoring Roadmap

To transition this proof of concept into an enterprise-grade, production-ready server engine, the following areas have been identified for future refactoring:

### 1. Bukkit API vs. Console Dispatching
* **Current State:** Portions of player initialization and team modifications rely on console command dispatch (`server.dispatchCommand`).
* **Target Refactor:** Migrate fully to native Bukkit APIs (`player.getInventory().clear()`, `Scoreboard` / `Team` API) to eliminate command parsing overhead and ensure type safety.

### 2. Entity Identity Consistency
* **Current State:** Player sessions are mapped using `String name`.
* **Target Refactor:** Transition all player tracking keys to `java.util.UUID` to maintain data integrity across username changes and mixed-mode network authentication.

### 3. Task Handle Governance
* **Current State:** Scheduled tasks in `TimeManager` run as detached `BukkitRunnable` instances.
* **Target Refactor:** Retain returned `BukkitTask` identifiers in a managed lifecycle map to allow clean task cancellation, pausing, and teardown during `/reload` or server shutdown.

### 4. Dynamic Chat Serialization
* **Current State:** Custom JSON serialization via `MinecraftTextBuilder`.
* **Target Refactor:** Migrate to modern native component frameworks (such as the Kyori Adventure API or Bungee Chat Component API) to eliminate string-building vulnerabilities.

---

[Return to README](README.md)

