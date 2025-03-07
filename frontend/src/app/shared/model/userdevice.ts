export interface UserDevice {
    id: number;
    deviceName: string;
    descriptionDevice: string;
    statusDevice: string;
    username: string;
    role: string;
    userId: string;
    deviceId: string;
    assignedAt: string;
    returnedAt: string;
}

export const USERDEVICE_KEYS = [
    'deviceName',
    'descriptionDevice',
    'statusDevice',
    'username',
    'role',
    'assignedAt',
    'returnedAt'
];