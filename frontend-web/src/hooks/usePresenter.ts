import { IAvaliation, IEvent, IParticipation, IUserCredentials } from "common/@Interfaces";
import { useAxios } from "hooks/useAxios";

export function usePresenter() {
    const axios = useAxios();

    return {
        signIn: async (username: string, password: string) => {
            const res = await axios.post<IUserCredentials>('/auth/authenticate', {
                username, password
            });
            return res.data;
        },
        findUserParticipations: async (userId: number) => {
            return (await axios.get<IParticipation[]>(`/users/participations/${userId}`)).data;
        },
        addJurorParticipation: async (userId: number, joinCode: string, jurorCode: string) => {
            const res = await axios.post<IParticipation>(`/participations/juror`, {
                userId, joinCode, jurorCode
            });
            return res.data;
        },
        removeParticipation: async (userId: number, eventId: number) => {
            await axios.delete('/participations', {
                params: { userId, eventId }
            });
        },
        findEvent: async (eventId: number) => {
            return (await axios.get<IEvent>(`/events/${eventId}`)).data;
        },
        addAvaliationToTeam: async (userId: number, teamId: number, value: number) => {
            const res = await axios.put<IAvaliation>('/avaliations', {
                userId, teamId, value
            });
            return res.data;
        }
    }
}