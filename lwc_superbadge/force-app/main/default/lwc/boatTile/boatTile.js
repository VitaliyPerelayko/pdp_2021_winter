import {api, LightningElement, track} from 'lwc';

const TILE_WRAPPER_SELECTED_CLASS = 'tile-wrapper selected';
const TILE_WRAPPER_UNSELECTED_CLASS = 'tile-wrapper';
const TEST_LIST = [
    {key: 1, value: "one"},
    {key: 2, value: "two"},
    {key: 3, value: "three"},
    {key: 4, value: "four"},
    {key: 5, value: "five"},
    {key: 6, value: "six"},
    {key: 7, value: "seven"},
    {key: 8, value: "eight"}
]
export default class BoatTile extends LightningElement {

    @api
    boat;
    @api
    selectedBoatId;

    @track
    testList = TEST_LIST;

    get backgroundStyle() {
        return 'background-image:url(' + this.boat.Picture__c + ')';
    }

    get tileClass() {
        if (this.boat.Id === this.selectedBoatId) {
            return TILE_WRAPPER_SELECTED_CLASS;
        }
        return TILE_WRAPPER_UNSELECTED_CLASS;
    }

    // Fires event with the Id of the boat that has been selected.
    selectBoat() {
        this.selectedBoatId = this.boat.Id;
        const boatselect = new CustomEvent('boatselect', {
            detail: {
                boatId: this.selectedBoatId
            }
        });
        this.dispatchEvent(boatselect);
    }

    handleForCheckbox(event) {
        const t = event.target;
        console.log(t.id);
        if (t.checked) {
            t.parentElement.style.color = "red";
        } else {
            t.parentElement.style.color = "black";
        }
    }
}