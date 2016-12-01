/*
 *
 * We might be able to change the return statements to just directly modifying the given assignment score
 *
 */
public class Constraints
{
	/* group heads should have a large office
	 *
	 * Applies to Group Heads only
	 *
	 * assign is the assignment of the group head
	 *
	 */
	public static void constraint1(Assignment assign)
	{
		// IF the assigned room is not large
		if (!assign.room.getRoomSize().equals("large"))
			assign.score += -40;
	}


	/* group heads should be close to all members of their group
	 *
	 * Applies to all members of the group
	 *
	 *
	 *
	 */
	public static void constraint2(Assignment assign)
	{
		int value = 0;

		// For each group that the assigned person belongs to
		for (Grp group : assign.person.getGroupsList()) {
			// For each group head in the group
			for (Person head : group.getGroupHeads()) {
				// If the group head is assigned and not close to the assigned person
				if (head.getRoom() != null && !head.getRoom().getCloseRooms().contains(assign.room))
					value += -2;
			}
		}

		// If assign is a group head
		for (Grp group : assign.person.getGroupHeadList()) {
			// For each person in the group
			for (Person groupmember : group.getGroupMembers()) {
				// If the person is assigned and not close the the assigned group head
				if (groupmember.getRoom() != null && !groupmember.getRoom().getCloseRooms().contains(assign.room))
					value += -2;
			}
		}

		assign.score += value;
	}

	/* group heads should be located close to at least one secretary in the group
	 * 
	 * Applies to group heads and secretaries
	 *
	 *
	 */
	public static void constraint3sec(Assignment assign)
	{
		int value = 0;


		// for each group that the secretary is in
		for (Grp group : assign.person.getGroupsList()) {
			// for each group head in that group
			for (Person head : group.getGroupHeads()) {
				if (head.getRoom() != null)
				{
					// if the head is their own secretary
					boolean b = head.getSecretary();
					// for each secretary in the group
					for (Person secretary : group.getGroupSecretaries()) {
						// if the secretary is unassigned or close to the group head
						if (assign.person.equals(secretary))
						{
							b = b || head.getRoom().getCloseRooms().contains(assign.room);
						}
						else
							b = b || secretary.getRoom() == null || head.getRoom().getCloseRooms().contains(secretary.getRoom());
					}

					if (!b)
						value += -30;
				}
			}
		}
		assign.score += value;
	}

	public static void constraint3grphead(Assignment assign)
	{
		int value = 0;

		// If the assigned person is a group head
		for (Grp group : assign.person.getGroupHeadList()) {
			boolean b = assign.person.getSecretary();
			// for each secretary in the group
			for (Person secretary : group.getGroupSecretaries()) {
				// if the secretary is unassigned or close to the group head
				b = b || secretary.getRoom() == null || assign.room.getCloseRooms().contains(secretary.getRoom());
			}

			if (!b)
				value += -30;
		}

		assign.score += value;
	}

	/* secretaries should share offices with other secretaries
	 *
	 * Currently set up so that if they share an office it should be with a secretary
	 *
	 * Applies only to secretaries
	 *
	 */
	public static void constraint4(Assignment assign)
	{
		// if the room that that they are being assigned to has a person who is not a secretary
		if (!assign.room.getAssigned().isEmpty() && !assign.room.getAssigned().get(0).getSecretary())
			assign.score += -5;
	}


	/* managers should be close to at least one secretary in their group
	 *
	 * 
	 *
	 *
	 *
	 */
	public static void constraint5(Assignment assign)
	{
		int value = 0;

		// if the assigned person is a secretary
		if (assign.person.getSecretary()) {
			// for each group that the secretary is in
			for (Grp group : assign.person.getGroupsList()) {
				// for each manager in that group
				for (Person head : group.getGroupManagers()) {
					if (head.getRoom() != null)
					{
						// if the head is their own secretary
						boolean b = false;
						// for each secretary in the group
						for (Person secretary : group.getGroupSecretaries()) {
							// if the secretary is unassigned or close to the manager
							if (assign.person.equals(secretary))
							{
								b = b || head.getRoom().getCloseRooms().contains(assign.room);
							}
							else
								b = b || secretary.getRoom() == null || head.getRoom().getCloseRooms().contains(secretary.getRoom());
						}

						if (!b)
							value += -20;
					}
				}
			}
		}

		// If the assigned person is a manager
		if (assign.person.getManager())
			for (Grp group : assign.person.getGroupsList()) {
				boolean b = false;
				// for each secretary in the group
				for (Person secretary : group.getGroupSecretaries()) {
					// if the secretary is unassigned or close to the group head
					b = b || secretary.getRoom() == null || assign.room.getCloseRooms().contains(secretary.getRoom());
				}

				if (!b)
					value += -20;
			}

		assign.score += value;
	}


	/* managers should be close to their group's head
	 *
	 * Applies to managers and group heads
	 *
	 *
	 *
	 */
	public static void constraint6manager(Assignment assign)
	{
		int value = 0;

		// for each group that they are in
		for (Grp group : assign.person.getGroupsList()) {
			// for each group head in the group
			for (Person head : group.getGroupHeads()) {
				// if the group head is assigned and not close to the manager
				if (head.getRoom() != null && !head.getRoom().getCloseRooms().contains(assign.room))
					value += -20;
			}
		}
		assign.score += value;
	}


	public static void constraint6grphead(Assignment assign)
	{
		int value = 0;

		// if they are a group head
		for (Grp group : assign.person.getGroupHeadList()) {
			// for each manager in their group
			for (Person manager : group.getGroupManagers()) {
				// if the manager is assigned and not close to the group head
				if (manager.getRoom() != null && !manager.getRoom().getCloseRooms().contains(assign.room))
					value += -20;
			}
		}

		assign.score += value;
	}

	/* managers should be close to all members of their group
	 *
	 * Applies to everybody
	 *
	 *
	 *
	 */
	public static void constraint7(Assignment assign)
	{
		int value = 0;

		// if they are manager
		if (assign.person.getManager()) {
			// for each group of the manager
			for (Grp scrubpen : assign.person.getGroupsList())
			{
				// for each person in their group
				for (Person scrub : scrubpen.getGroupMembers()) {
					// if the person is assigned and not close to the manager
					if (scrub.getRoom() != null && scrub.getRoom().getCloseRooms().contains(assign.room))
						value += -2;
				}
			}
		}

		// for each group that they are in
		for (Grp scrubpen : assign.person.getGroupsList()) {
			// for each manager in the group
			for (Person scrublord : scrubpen.getGroupManagers()) {
				// if the manager is assigned and not close to the person
				if (scrublord.getRoom() != null && scrublord.getRoom().getCloseRooms().contains(assign.room))
					value += -2;
			}
		}

		assign.score += value;
	}


	/* the heads of projects should be close to all members of their project
	 *
	 * Applies to everybody
	 *
	 *
	 */
	public static void constraint8(Assignment assign)
	{
		int value = 0;

		// if they are a project head
		for (Project prj : assign.person.getProjectHead()) {
			// for each person in their project
			for (Person scrub : prj.getProjectMembers()) {
				// if the person is assigned and not close to the manager
				if (scrub.getRoom() != null && scrub.getRoom().getCloseRooms().contains(assign.room))
					value += -5;
			}
		}

		// for each project that they are in
		for (Project scrubpen : assign.person.getProjectsList()) {
			// for each projecthead in the project
			for (Person scrublord : scrubpen.getProjectHeads()) {
				// if the project head is assigned and not close to the person
				if (scrublord.getRoom() != null && scrublord.getRoom().getCloseRooms().contains(assign.room))
					value += -5;
			}
		}

		assign.score += value;
	}

	/* the heads of large projects should be close to at least one secretary in their group
	 *
	 * applies to large project heads and secretaries
	 *
	 *
	 *
	 */
	public static void constraint9sec(Assignment assign)
	{
		int value = 0;

		// for each group that the secretary is in
		for (Grp group : assign.person.getGroupsList()) {
			// for each group head in that group
			for (Person head : group.getLargeProjectHeads()) {
				if (head.getRoom() != null)
				{
					// if the head is their own secretary
					boolean b = head.getSecretary();
					// for each secretary in the group
					for (Person secretary : group.getGroupSecretaries()) {
						// if the secretary is unassigned or close to the group head
						if (assign.person.equals(secretary))
						{
							b = b || head.getRoom().getCloseRooms().contains(assign.room);
						}
						else
							b = b || secretary.getRoom() == null || head.getRoom().getCloseRooms().contains(secretary.getRoom());
					}

					if (!b)
						value += -10;
				}
			}
		}
		assign.score += value;
	}

	public static void constraint9largeprj(Assignment assign)
	{
		int value = 0;


		// If the assigned person is a large project head
		for (Project prj : assign.person.getProjectHead()) {
			if (prj.getLarge())
			{
				for (Grp group : assign.person.getGroupsList())
				{
					boolean b = assign.person.getSecretary();
					// for each secretary in the group
					for (Person secretary : group.getGroupSecretaries()) {
						// if the secretary is unassigned or close to the group head
						b = b || secretary.getRoom() == null || assign.room.getCloseRooms().contains(secretary.getRoom());
					}

					if (!b)
						value += -10;
				}
			}
		}

		assign.score += value;
	}

	/* the heads of large projects should be close to the head of their group
	 *
	 * applies to large project heads and group heads
	 *
	 *
	 *
	 */
	public static void constraint10largeprj(Assignment assign)
	{
		int value = 0;

		// If they are a large project head
		for (Project prj : assign.person.getProjectHead()) {
			if (prj.getLarge())
			{
				// for each group that they are in
				for (Grp group : assign.person.getGroupsList()) {
					// for each group head in the group
					for (Person head : group.getGroupHeads()) {
						// if the group head is assigned and not close to the LPH
						if (head.getRoom() != null && head.getRoom().getCloseRooms().contains(assign.room))
							value += -10;
					}
				}
			}

		}
		assign.score += value;
	}
	public static void constraint10grphead(Assignment assign)
	{
		int value = 0;

		// if they are a group head
		for (Grp group : assign.person.getGroupHeadList()) {
			// for each LPH in their group
			for (Person lph : group.getLargeProjectHeads()) {
				// if the LPH is assigned and not close to the group head
				if (lph.getRoom() != null && lph.getRoom().getCloseRooms().contains(assign.room))
					value += -10;
			}
		}

		assign.score += value;
	}


	/* a smoker shouldn't share an office with a non-smoker
	 *
	 * applies to everyone
	 *
	 *
	 *
	 */
	public static void constraint11(Assignment assign)
	{
		// If they are a smoker and share a room with a no smoker
		if (assign.person.getSmoker() && assign.room.getAssigned().size() > 0 && !assign.room.getAssigned().get(0).getSmoker())
			assign.score += -50;

		// IF they are a non smoker and share a room with a smoker
		else if (!assign.person.getSmoker() && assign.room.getAssigned().size() > 0 && assign.room.getAssigned().get(0).getSmoker())
			assign.score += -50;
	}

	/* members of the same project should not share an office (encourages synergy between projects)
	 *
	 * applies to everyone
	 */
	public static void constraint12(Assignment assign)
	{
		// if they share and room and if the other person is in the same project
		if (assign.room.getAssigned().size() != 0)
		{
			boolean b = false;
			for (Project prj : assign.person.getProjectsList())
			{
				b = b || assign.room.getAssigned().get(0).getProjectsList().contains(prj);

			}
			if (b)
				assign.score += -7;
		}
	}

	/* if a non-secretary hacker/non-hacker shares an office, then he/she should share with another hacker/non- hacker
	 *
	 * applies to everyone
	 */
	public static void constraint13(Assignment assign)
	{
		// if they are a secretary or share a room with a secretary
		if (assign.person.getSecretary() || assign.room.getAssigned().size() == 0 || assign.room.getAssigned().get(0).getSecretary())
			return;

		// if they are a hacker and share a room with non hacker
		if (assign.person.getHacker() && !assign.room.getAssigned().get(0).getHacker())
			assign.score += -2;

		// if they are a non hacker and share a room with a hacker
		else if (!assign.person.getHacker() && assign.room.getAssigned().get(0).getHacker())
			assign.score += -2;
	}

	/* people prefer to have their own offices
	 *
	 * applies to everyone
	 */
	public static void constraint14(Assignment assign)
	{
		// if they share an office
		if (assign.room.getAssigned().size()>0)
			assign.score += -4;
	}

	/* if two people share an office, they should work together
	 *
	 * applies to everyone
	 */
	public static void constraint15(Assignment assign)
	{
		// if they share an office and dont work together
		if (!assign.room.getAssigned().isEmpty() && assign.person.getWorksWith().contains(assign.room.getAssigned().get(0)))
			assign.score += -3;
	}

	/* two people shouldn't share a small room
	 *
	 * applies to everyone
	 */
	public static void constraint16(Assignment assign)
	{
		// if they share an office and it is small
		if (assign.room.getAssigned().size()>0 && assign.room.getRoomSize().equals("small"))
			assign.score += -25;
	}


}



























