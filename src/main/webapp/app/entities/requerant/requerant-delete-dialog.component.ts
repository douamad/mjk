import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRequerant } from 'app/shared/model/requerant.model';
import { RequerantService } from './requerant.service';

@Component({
  templateUrl: './requerant-delete-dialog.component.html',
})
export class RequerantDeleteDialogComponent {
  requerant?: IRequerant;

  constructor(protected requerantService: RequerantService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.requerantService.delete(id).subscribe(() => {
      this.eventManager.broadcast('requerantListModification');
      this.activeModal.close();
    });
  }
}
