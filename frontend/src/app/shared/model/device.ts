export interface Device {
    deviceId: string;
    name: string;
    description: string;
    status: string;
    createdAt: string;
    updatedAt: string;
}

export const DEVICE_KEYS: (keyof Device)[] = [
    'deviceId',
    'name',
    'description',
    'status',
    'createdAt',
    'updatedAt'
  ];

