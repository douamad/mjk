import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICreance } from 'app/shared/model/creance.model';
import { CreanceService } from './creance.service';

@Component({
  templateUrl: './creance-delete-dialog.component.html',
})
export class CreanceDeleteDialogComponent {
  creance?: ICreance;

  constructor(protected creanceService: CreanceService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.creanceService.delete(id).subscribe(() => {
      this.eventManager.broadcast('creanceListModification');
      this.activeModal.close();
    });
  }
}
