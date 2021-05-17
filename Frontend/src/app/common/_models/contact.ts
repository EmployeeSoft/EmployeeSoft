export class Contact {
  id: number;
  fullName: string;
  phone: string;
  relationship: string;
  title: string;
  address: string;
  isReference: boolean;
  isEmergency: boolean;
  isLandlord: boolean;

  constructor(id: number, fullName: string, phone: string,
              relationship: string, address: string) {
    this.id = id;
    this.fullName = fullName;
    this.phone = phone;
    this.relationship = relationship;
    this.address = address;
  }
}
