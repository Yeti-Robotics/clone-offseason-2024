# A treatise upon the development of high quality robots

## Lynk Subsystems
- Indexer
- ~~Climber~~
- Intake
- LED
- Shooter
- Swerve
- Vision

## States

### Shooter states

- Stopped
- Speaker shot
  - Vision based (dependent on vision subsystem)
  - Bumpfire?
- Trap shot
- Amp shot
- Shuttle
- ~~Ready to shoot~~
- Dump

### Indexer states

- Staged
- ~~Staging~~
- Not staged

### Intake states
- In
- Dump
- Stopped

### Overall state machine
<img src="https://media.discordapp.net/attachments/408795818963894274/1250233667926888478/image.jpg?ex=666a3256&is=6668e0d6&hm=8624bb7935e82fa337f0977df8184b1f6a81cf463c8c27a9d132e1c127b40cfb&=&format=webp&width=731&height=548" />
