import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IObjetAssistance } from 'app/shared/model/objet-assistance.model';
import { ObjetAssistanceService } from './objet-assistance.service';

@Component({
  templateUrl: './objet-assistance-delete-dialog.component.html',
})
export class ObjetAssistanceDeleteDialogComponent {
  objetAssistance?: IObjetAssistance;

  constructor(
    protected objetAssistanceService: ObjetAssistanceService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.objetAssistanceService.delete(id).subscribe(() => {
      this.eventManager.broadcast('objetAssistanceListModification');
      this.activeModal.close();
    });
  }
}
